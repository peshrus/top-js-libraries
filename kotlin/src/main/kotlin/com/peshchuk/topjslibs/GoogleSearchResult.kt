package com.peshchuk.topjslibs

private val SEARCH_RESULT_LINK =
    """<a href="([^"]+?)"[^>]+?data-ved="[^"]+?"[^>]+?onmousedown="[^"]+?"><br>""".toRegex()

class GoogleSearchResult(private val linksLimit: Int, private val fetchHtml: () -> String) {

    fun getLinks(): List<String> {
        val html = fetchHtml()
        val matchResults = SEARCH_RESULT_LINK.findAll(html)

        return matchResults
            .take(linksLimit)
            .filter { matchResult -> matchResult.groups.size > 1 }
            .map { matchResult -> matchResult.groups[1]?.value!! }
            .toList()
    }
}