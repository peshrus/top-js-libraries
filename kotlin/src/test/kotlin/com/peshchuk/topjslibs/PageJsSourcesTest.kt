package com.peshchuk.topjslibs

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

@Suppress("EXPERIMENTAL_API_USAGE")
internal class PageJsSourcesTest {

    private val mainThreadSurrogate = newSingleThreadContext("Test thread")

    @BeforeTest
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }

    @Test
    fun get() {
        runBlocking {
            launch(Dispatchers.Main) {
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
    }
}
