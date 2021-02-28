package com.peshchuk.topjslibs

import kotlin.test.Test
import kotlin.test.assertEquals

internal class TopJsLibrariesTest {

    @Test
    fun count() {
        // Arrange
        val expected = linkedMapOf(
            Pair("base.min;v35893530.js", 2),
            Pair("modules.min;v35893530.js", 2),
            Pair("webtk.min;v35893530.js", 2),
            Pair("webtrekk_cookieControl.min;v35893530.js", 2),
            Pair("application-0d7a7bcfe504533ad327.js", 1)
        )
        val topJsLibraries = TopJsLibraries(linksLimit = 5, topLimit = 5) { url -> fetchHtml(url) }

        // Act
        val actual = topJsLibraries.count("test")

        // Assert
        assertEquals(expected, actual)
    }

    private fun fetchHtml(url: String): String {
        return when (url) {
            "https://www.google.com/search?q=test" -> Util.getHtmlFrom("google.com.html")

            "https://www.test.de/" -> Util.getHtmlFrom("test.de.html")
            "https://www.test.de/shop/test-hefte/" -> Util.getHtmlFrom("test-hefte.html")
            "https://www.test.de/thema/" -> Util.getHtmlFrom("thema.html")
            "https://www.oekotest.de/" -> Util.getHtmlFrom("oekotest.de.html")
            "https://de.wikipedia.org/wiki/Test_(Zeitschrift)" -> Util.getHtmlFrom("zeitschrift.html")

            else -> ""
        }
    }
}