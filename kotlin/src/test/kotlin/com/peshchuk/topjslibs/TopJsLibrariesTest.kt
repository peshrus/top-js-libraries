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

        // Act
        val actual = TopJsLibraries(5, 5) { uri -> fetchHtml(uri) }.count("test")

        // Assert
        assertEquals(expected, actual)
    }

    private fun fetchHtml(uri: String): String {
        return when (uri) {
            "https://www.google.com/search?q=test" -> Util.getHtml("google.com.html")

            "https://www.test.de/" -> Util.getHtml("test.de.html")
            "https://www.test.de/shop/test-hefte/" -> Util.getHtml("test-hefte.html")
            "https://www.test.de/thema/" -> Util.getHtml("thema.html")
            "https://www.oekotest.de/" -> Util.getHtml("oekotest.de.html")
            "https://de.wikipedia.org/wiki/Test_(Zeitschrift)" -> Util.getHtml("zeitschrift.html")

            else -> ""
        }
    }
}