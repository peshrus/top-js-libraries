package com.peshchuk.topjslibs

import org.junit.runner.RunWith
import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class GoogleSearchResultTest extends AnyFunSuite {

  test("should get the first 5 links from the Google search result") {
    // Arrange
    val expected = List(
      "https://www.test.de/",
      "https://www.test.de/shop/test-hefte/",
      "https://www.test.de/thema/",
      "https://www.oekotest.de/",
      "https://de.wikipedia.org/wiki/Test_(Zeitschrift)"
    )
    val googleSearchResult = new GoogleSearchResult(linksLimit = 5,
      fetchHtml = () => Util.getHtmlFrom("google.com.html"))

    // Act
    val actual = googleSearchResult.getLinks

    // Assert
    assert(expected == actual)
  }
}
