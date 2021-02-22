package com.peshchuk.topjslibs

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.readText
import kotlinx.coroutines.runBlocking
import java.util.logging.Logger

private val LOGGER = Logger.getLogger("main")

fun main(args: Array<String>) {
    require(args.isNotEmpty()) { "Specify the Google search query, please" }

    val searchStr = args[0]
    LOGGER.info("Query: $searchStr")

    LOGGER.info("Start")

    val topJsLibraries = TopJsLibraries(5, 5) { uri -> fetchHtml(uri) }
    val count = topJsLibraries.count(searchStr)

    LOGGER.info("Finish")

    count.forEach { (key, value) -> println("[$value] $key") }
}

private fun fetchHtml(uri: String): String {
    LOGGER.fine("Fetch: $uri")

    return runBlocking {
        HttpClient(CIO).use { client ->
            val response: HttpResponse = client.get(uri) {
                headers {
                    append(
                        "User-Agent",
                        "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:84.0) Gecko/20100101 Firefox/84.0"
                    )
                    append("Referer", "http://www.goole.com")
                }
            }

            val status = response.status.value

            if (status < 200 || status >= 400) {
                LOGGER.severe("HTTP Status $status: $uri")
                ""
            } else {
                response.readText()
            }
        }
    }
}