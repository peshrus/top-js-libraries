package com.peshchuk.topjslibs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import org.junit.jupiter.api.Test;

public class PageJsSourcesTest {

  @Test
  public void call() {
    // Arrange
    final var expected = new HashSet<String>();
    expected.add("base.min;v35620016.js");
    expected.add("modules.min;v35620016.js");
    expected.add("webtrekk_cookieControl.min;v35620016.js");
    expected.add("webtk.min;v35620016.js");

    // Act
    final var actual = new PageJsSources(() -> Util.getHtmlFrom("test.de.html")).call();

    // Assert
    assertEquals(expected, actual);
  }

}