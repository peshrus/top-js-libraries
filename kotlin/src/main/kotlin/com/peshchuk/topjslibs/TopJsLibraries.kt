package com.peshchuk.topjslibs

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

private const val SEARCH_URL = "https://www.google.com/search?q="

class TopJsLibraries(
    private val linksLimit: Int,
    private val topLimit: Int,
    private val fetchHtml: suspend (String) -> String
) {

    suspend fun count(searchStr: String): Map<String, Int> {
        val links = GoogleSearchResult(linksLimit) { fetchHtml(SEARCH_URL + searchStr) }.getLinks()
        return coroutineScope {
            val pagesJsSources = links.map { link ->
                async {
                    PageJsSources { fetchHtml(link) }.get()
                }
            }
            val sortByCountDescSrcAsc =
                compareByDescending<Pair<String, Int>> { (_, value) -> value }
                    .thenComparing { (key, _) -> key }

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
