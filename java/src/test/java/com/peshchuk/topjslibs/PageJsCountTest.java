package com.peshchuk.topjslibs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import org.junit.jupiter.api.Test;

public class PageJsCountTest {

  @Test
  public void call() {
    // Arrange
    final var expected = new HashMap<String, Integer>();
    expected.put("base.min;v35620016.js", 1);
    expected.put("modules.min;v35620016.js", 1);
    expected.put("webtrekk_cookieControl.min;v35620016.js", 1);
    expected.put("webtk.min;v35620016.js", 1);

    // Act
    final var actual = new PageJsCount(() -> Util.getHtml("test.de.html")).call();

    // Assert
    assertEquals(expected, actual);
  }

}