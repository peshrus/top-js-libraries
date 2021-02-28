package com.peshchuk.topjslibs

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

private const val SEARCH_URL = "https://www.google.com/search?q="

class TopJsLibraries(
    private val linksLimit: Int,
    private val topLimit: Int,
    private val fetchHtml: (String) -> String
) {

    fun count(searchStr: String): Map<String, Int> {
        val links = GoogleSearchResult(linksLimit) { fetchHtml(SEARCH_URL + searchStr) }.getLinks()
        val pagesJsSources = links.map { link ->
            GlobalScope.async {
                PageJsSources { fetchHtml(link) }.get()
            }
        }
        val sortByCountDescSrcAsc = compareByDescending<Pair<String, Int>> { (_, value) -> value }
            .thenComparing { (key, _) -> key }

        return runBlocking {
            pagesJsSources
                .flatMap { it.await() }
                .groupingBy { it }
                .eachCount()
                .toList()
                .sortedWith(sortByCountDescSrcAsc)
                .take(topLimit)
                .toMap()
        }
    }
}