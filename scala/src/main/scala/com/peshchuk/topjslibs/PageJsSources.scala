package com.peshchuk.topjslibs

class PageJsSources(private val fetchHtml: () => String) {

  def get: Set[String] = {
    val html = fetchHtml()
    val matchData = PageJsSources.SCRIPT_SRC.findAllIn(html).matchData

    matchData
      .filter(_.groupCount > 0)
      .map(_.group(1))
      .toSet
  }
}

object PageJsSources {
  private final val SCRIPT_SRC = """<script[^>]+?src=["'](?:[^"']+/)?([^"']+?)["']""".r
}
