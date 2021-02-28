package com.peshchuk.topjslibs;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.function.Supplier;
import java.util.regex.Pattern;

public class PageJsSources implements Callable<Set<String>> {

  private static final Pattern SCRIPT_SRC = Pattern
      .compile("<script[^>]+src=[\"']([^\"']+/)?([^\"']+)[\"']");

  private final Supplier<String> htmlSupplier;

  public PageJsSources(final Supplier<String> htmlSupplier) {
    this.htmlSupplier = htmlSupplier;
  }

  @Override
  public Set<String> call() {
    final var html = htmlSupplier.get();
    final var matcher = SCRIPT_SRC.matcher(html);
    final var result = new HashSet<String>();

    while (matcher.find()) {
      if (matcher.groupCount() < 2) {
        continue;
      }

      final var src = matcher.group(2);
      result.add(src);
    }

    return Collections.unmodifiableSet(result);
  }
}
