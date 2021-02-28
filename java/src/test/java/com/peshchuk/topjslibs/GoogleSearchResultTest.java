package com.peshchuk.topjslibs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class GoogleSearchResultTest {

  @Test
  public void getLinks() {
    // Arrange
    final var expected = Arrays.asList(
        "https://www.test.de/",
        "https://www.test.de/shop/test-hefte/",
        "https://www.test.de/thema/",
        "https://www.oekotest.de/",
        "https://de.wikipedia.org/wiki/Test_(Zeitschrift)"
    );

    // Act
    final var actual = new GoogleSearchResult(5, () -> Util.getHtmlFrom("google.com.html"))
        .getLinks();

    // Assert
    assertEquals(expected, actual);
  }
}