package com.peshchuk.topjslibs

import kotlin.test.Test
import kotlin.test.assertEquals

internal class GoogleSearchResultTest {

    @Test
    fun getLinks() {
        // Arrange
        val expected = listOf(
            "https://www.test.de/",
            "https://www.test.de/shop/test-hefte/",
            "https://www.test.de/thema/",
            "https://www.oekotest.de/",
            "https://de.wikipedia.org/wiki/Test_(Zeitschrift)"
        )

        // Act
        val actual = GoogleSearchResult(5) { Util.getHtml("google.com.html") }.getLinks()

        // Assert
        assertEquals(expected, actual)
    }
}