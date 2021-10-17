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
internal class TopJsLibrariesTest {

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
    fun count() {
        runBlocking {
            launch(Dispatchers.Main) {
                // Arrange
                fun fetchHtml(url: String): String {
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

                val expected = linkedMapOf(
                    Pair("base.min;v35893530.js", 2),
                    Pair("modules.min;v35893530.js", 2),
                    Pair("webtk.min;v35893530.js", 2),
                    Pair("webtrekk_cookieControl.min;v35893530.js", 2),
                    Pair("application-0d7a7bcfe504533ad327.js", 1)
                )
                val topJsLibraries =
                    TopJsLibraries(linksLimit = 5, topLimit = 5) { url -> fetchHtml(url) }

                // Act
                val actual = topJsLibraries.count("test")

                // Assert
                assertEquals(expected, actual)
            }
        }
    }
}
