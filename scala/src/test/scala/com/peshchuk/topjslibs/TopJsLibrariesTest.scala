package com.peshchuk.topjslibs

import org.junit.runner.RunWith
import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.junit.JUnitRunner

import scala.collection.immutable

@RunWith(classOf[JUnitRunner])
class TopJsLibrariesTest extends AnyFunSuite {

  test("should return top 5 most popular JS libraries from the search result") {
    // Arrange
    val expected = immutable.ListMap(
      "base.min;v35893530.js" -> 2,
      "modules.min;v35893530.js" -> 2,
      "webtk.min;v35893530.js" -> 2,
      "webtrekk_cookieControl.min;v35893530.js" -> 2,
      "application-0d7a7bcfe504533ad327.js" -> 1
    )
    val topJsLibraries = new TopJsLibraries(linksLimit = 5, topLimit = 5, fetchHtml = fetchHtml)

    // Act
    val actual = topJsLibraries.count("test")

    // Assert
    assert(expected == actual)
  }

  private def fetchHtml(url: String): String = url match {
    case "https://www.google.com/search?q=test" => Util.getHtmlFrom("google.com.html")

    case "https://www.test.de/" => Util.getHtmlFrom("test.de.html")
    case "https://www.test.de/shop/test-hefte/" => Util.getHtmlFrom("test-hefte.html")
    case "https://www.test.de/thema/" => Util.getHtmlFrom("thema.html")
    case "https://www.oekotest.de/" => Util.getHtmlFrom("oekotest.de.html")
    case "https://de.wikipedia.org/wiki/Test_(Zeitschrift)" => Util.getHtmlFrom("zeitschrift.html")

    case _ => ""
  }
}
