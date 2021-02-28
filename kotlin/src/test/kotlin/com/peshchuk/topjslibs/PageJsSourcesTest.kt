package com.peshchuk.topjslibs

import kotlin.test.Test
import kotlin.test.assertEquals

internal class PageJsSourcesTest {

    @Test
    fun get() {
        // Arrange
        val expected = setOf(
            "base.min;v35620016.js",
            "modules.min;v35620016.js",
            "webtrekk_cookieControl.min;v35620016.js",
            "webtk.min;v35620016.js"
        )
        val pageJsSources = PageJsSources { Util.getHtmlFrom("test.de.html") }

        // Act
        val actual = pageJsSources.get()

        // Assert
        assertEquals(expected, actual)
    }
}