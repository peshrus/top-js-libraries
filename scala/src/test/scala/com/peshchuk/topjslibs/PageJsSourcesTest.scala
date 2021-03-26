package com.peshchuk.topjslibs

import org.junit.runner.RunWith
import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class PageJsSourcesTest extends AnyFunSuite {

  test("should get all JS sources from the page") {
    // Arrange
    val expected = Set(
      "base.min;v35620016.js",
      "modules.min;v35620016.js",
      "webtrekk_cookieControl.min;v35620016.js",
      "webtk.min;v35620016.js"
    )
    val pageJsSources = new PageJsSources(() => Util.getHtmlFrom("test.de.html"))

    // Act
    val actual = pageJsSources.get

    // Assert
    assert(expected == actual)
  }
}
