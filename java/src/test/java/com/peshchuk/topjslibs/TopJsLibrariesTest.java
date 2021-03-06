package com.peshchuk.topjslibs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class TopJsLibrariesTest {

  @Test
  public void count() {
    // Arrange
    final var expected = new LinkedHashMap<String, Integer>();
    expected.put("base.min;v35893530.js", 2);
    expected.put("modules.min;v35893530.js", 2);
    expected.put("webtk.min;v35893530.js", 2);
    expected.put("webtrekk_cookieControl.min;v35893530.js", 2);
    expected.put("application-0d7a7bcfe504533ad327.js", 1);

    // Act
    final Map<String, Integer> actual;
    try (final var topJsLibraries = new TopJsLibraries(5, 5, TopJsLibrariesTest::fetchHtml)) {
      actual = topJsLibraries.count("test");
    }

    // Assert
    assertEquals(expected, actual);
  }

  private static String fetchHtml(final String url) {
    return switch (url) {
      case "https://www.google.com/search?q=test" -> Util.getHtmlFrom("google.com.html");

      case "https://www.test.de/" -> Util.getHtmlFrom("test.de.html");
      case "https://www.test.de/shop/test-hefte/" -> Util.getHtmlFrom("test-hefte.html");
      case "https://www.test.de/thema/" -> Util.getHtmlFrom("thema.html");
      case "https://www.oekotest.de/" -> Util.getHtmlFrom("oekotest.de.html");
      case "https://de.wikipedia.org/wiki/Test_(Zeitschrift)" -> Util
          .getHtmlFrom("zeitschrift.html");

      default -> "";
    };
  }
}