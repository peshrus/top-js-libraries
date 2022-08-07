package com.peshchuk.topjslibs

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(DelicateCoroutinesApi::class, ExperimentalCoroutinesApi::class)
@Suppress("EXPERIMENTAL_API_USAGE")
internal class GoogleSearchResultTest {

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
    fun getLinks() {
        runBlocking {
            launch(Dispatchers.Main) {
                // Arrange
                val expected = listOf(
                    "https://www.test.de/",
                    "https://www.test.de/shop/test-hefte/",
                    "https://www.test.de/thema/",
                    "https://www.oekotest.de/",
                    "https://de.wikipedia.org/wiki/Test_(Zeitschrift)"
                )
                val googleSearchResult =
                    GoogleSearchResult(linksLimit = 5) { Util.getHtmlFrom("google.com.html") }

                // Act
                val actual = googleSearchResult.getLinks()

                // Assert
                assertEquals(expected, actual)
            }
        }
    }
}
