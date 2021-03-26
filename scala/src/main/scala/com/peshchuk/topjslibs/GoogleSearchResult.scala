package com.peshchuk.topjslibs

class GoogleSearchResult(private val linksLimit: Int, private val fetchHtml: () => String) {

  def getLinks: List[String] = {
    val html = fetchHtml()
    val matchData = GoogleSearchResult.SEARCH_RESULT_LINK.findAllIn(html).matchData

    matchData
      .filter(_.groupCount > 0)
      .map(_.group(1))
      .slice(0, linksLimit)
      .toList
  }
}

object GoogleSearchResult {
  private final val SEARCH_RESULT_LINK =
    """<a href="([^"]+?)"[^>]+?data-ved="[^"]+?"[^>]+?onmousedown="[^"]+?"><br>""".r
}
