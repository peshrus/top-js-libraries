package com.peshchuk.topjslibs;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.function.Supplier;
import java.util.regex.Pattern;

public class PageJsCount implements Callable<Map<String, Integer>> {

  private static final Pattern SCRIPT_SRC = Pattern
      .compile("<script[^>]+src=[\"']([^\"']+/)?([^\"']+)[\"']");

  private final Supplier<String> htmlSupplier;

  public PageJsCount(final Supplier<String> htmlSupplier) {
    this.htmlSupplier = htmlSupplier;
  }

  @Override
  public Map<String, Integer> call() {
    final var html = htmlSupplier.get();
    final var matcher = SCRIPT_SRC.matcher(html);
    final var result = new HashMap<String, Integer>();

    while (matcher.find()) {
      final var src = matcher.group(2);
      result.put(src, result.computeIfAbsent(src, js -> 0) + 1);
    }

    return Collections.unmodifiableMap(result);
  }
}
