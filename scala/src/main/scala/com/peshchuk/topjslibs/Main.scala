package com.peshchuk.topjslibs

import scalaj.http.{BaseHttp, HttpOptions}

import java.util.logging.Logger

object Main extends App {

  private val LOGGER = Logger.getLogger("main")

  require(!args.isEmpty, "Specify the Google search query, please")

  val searchStr = args(0)
  LOGGER.info(s"Query: $searchStr")

  LOGGER.info("Start")

  val topJsLibraries =
    new TopJsLibraries(linksLimit = 5, topLimit = 5, fetchHtml = url => fetchHtml(url))
  val count = topJsLibraries.count(searchStr)

  LOGGER.info("Finish")

  count.foreachEntry((key, value) => println(s"[$value] $key"))

  private def fetchHtml(url: String): String = {
    LOGGER.fine(s"Fetch: $url")

    val response = HttpWithMozillaUserAgent(url)
      .header("Referer", "http://www.google.com")
      .option(HttpOptions.readTimeout(1000))
      .asString

    if (response.code < 200 || response.code >= 400) {
      LOGGER.severe(s"HTTP Status $response.code: $url")
      return ""
    }

    response.body
  }
}

object HttpWithMozillaUserAgent extends BaseHttp (
  userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:84.0) Gecko/20100101 Firefox/84.0",
)
