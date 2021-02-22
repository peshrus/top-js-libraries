package com.peshchuk.topjslibs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class TopJsLibraries implements AutoCloseable {

  private static final Logger LOGGER = Logger.getLogger(TopJsLibraries.class.getName());

  private static final String SEARCH_URL = "https://www.google.com/search?q=";

  private final int linksLimit;
  private final int topLimit;
  private final Function<String, String> fetchHtml;
  private final ExecutorService executorService;

  public TopJsLibraries(final int linksLimit, final int topLimit,
      final Function<String, String> fetchHtml) {
    this.linksLimit = linksLimit;
    this.topLimit = topLimit;
    this.fetchHtml = fetchHtml;
    // Number of threads = Number of Available Cores * (1 + Wait time / Service time)
    // The current implementation is a simplified version of the above formula
    this.executorService = Executors
        .newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);
  }

  @Override
  public void close() {
    executorService.shutdown();
  }

  public Map<String, Integer> count(final String searchStr) {
    final var googleResults = getGoogleResults(searchStr);
    final var pagesJsSources = new ArrayList<Future<Set<String>>>(linksLimit - 1);

    for (int i = 1; i < linksLimit; i++) {
      final var pageJsSources = executorService.submit(newPageJsSources(googleResults, i));
      pagesJsSources.add(pageJsSources);
    }

    final var firstPageJsNum = newPageJsSources(googleResults, 0).call()
        .stream()
        .collect(Collectors.toMap(js -> js, js -> 1));
    final var result = new HashMap<>(firstPageJsNum);
    pagesJsSources
        .stream()
        .map(TopJsLibraries::getJsSources)
        .flatMap(Collection::stream)
        .forEach(js -> result.merge(js, 1, Integer::sum));

    final var sortByCountDescSrcAsc = Entry
        .<String, Integer>comparingByValue()
        .reversed()
        .thenComparing(Entry.comparingByKey());

    return Collections.unmodifiableMap(result.entrySet()
        .stream()
        .sorted(sortByCountDescSrcAsc)
        .limit(topLimit)
        .collect(
            Collectors.toMap(Entry::getKey, Entry::getValue, Integer::sum, LinkedHashMap::new)));
  }

  private List<String> getGoogleResults(final String arg) {
    final var searchUri = SEARCH_URL + arg;
    return new GoogleSearchResult(linksLimit, () -> fetchHtml.apply(searchUri)).getLinks();
  }

  private PageJsSources newPageJsSources(final List<String> links, final int linkNum) {
    return new PageJsSources(() -> fetchHtml.apply(links.get(linkNum)));
  }

  private static Set<String> getJsSources(final Future<Set<String>> pageJsSources) {
    try {
      return pageJsSources.get();
    } catch (final InterruptedException | ExecutionException e) {
      LOGGER.log(Level.SEVERE, "Cannot count JS", e);
      return Collections.emptySet();
    }
  }
}
