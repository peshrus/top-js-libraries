package com.peshchuk.topjslibs

import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse.BodyHandlers
import java.time.Duration
import java.time.temporal.ChronoUnit
import java.util.logging.Level
import java.util.logging.Logger

private val LOGGER = Logger.getLogger("Main")

fun main(args: Array<String>) {
    require(args.isNotEmpty()) { "Specify the Google search query, please" }

    val searchStr = args[0]
    LOGGER.info("Query: $searchStr")

    LOGGER.info("Start")

    val topJsLibraries = TopJsLibraries(5, 5) { uri -> fetchHtml(uri) }
    val count: Map<String, Int> = topJsLibraries.count(searchStr)

    LOGGER.info("Finish")

    count.forEach { (key, value) -> println("[$value] $key") }
}

private fun fetchHtml(uri: String): String {
    LOGGER.fine("Fetch: $uri")

    val request = HttpRequest.newBuilder()
            .uri(URI.create(uri))
            .header("User-Agent",
                    "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:84.0) Gecko/20100101 Firefox/84.0")
            .header("Referer", "http://www.goole.com")
            .build()
    val httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .followRedirects(HttpClient.Redirect.ALWAYS)
            .connectTimeout(Duration.of(1, ChronoUnit.SECONDS))
            .build()

    return try {
        val response = httpClient.send(request, BodyHandlers.ofString())

        if (response.statusCode() < 200 || response.statusCode() >= 400) {
            throw Exception("HTTP Status " + response.statusCode() + ": " + uri)
        }

        response.body()
    } catch (e: Exception) {
        LOGGER.log(Level.SEVERE, "Cannot fetch HTML", e)
        ""
    }
}