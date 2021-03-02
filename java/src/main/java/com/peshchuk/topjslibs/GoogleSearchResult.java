package com.peshchuk.topjslibs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;
import java.util.regex.Pattern;

public class GoogleSearchResult {

  private static final Pattern SEARCH_RESULT_LINK = Pattern
      .compile("<a href=\"([^\"]+?)\"[^>]+?data-ved=\"[^\"]+?\"[^>]+?onmousedown=\"[^\"]+?\"><br>");

  private final int linksLimit;
  private final Supplier<String> htmlSupplier;

  public GoogleSearchResult(final int linksLimit, final Supplier<String> htmlSupplier) {
    this.linksLimit = linksLimit;
    this.htmlSupplier = htmlSupplier;
  }

  public List<String> getLinks() {
    final var html = htmlSupplier.get();
    final var matcher = SEARCH_RESULT_LINK.matcher(html);
    final var result = new ArrayList<String>(linksLimit);

    while (matcher.find() && result.size() < linksLimit) {
      if (matcher.groupCount() < 1) {
        continue;
      }

      final var href = matcher.group(1);
      result.add(href);
    }

    return Collections.unmodifiableList(result);
  }
}
