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

        // Act
        val actual = PageJsSources { Util.getHtml("test.de.html") }.get()

        // Assert
        assertEquals(expected, actual)
    }
}