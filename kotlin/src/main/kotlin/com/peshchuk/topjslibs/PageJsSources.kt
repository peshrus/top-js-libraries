package com.peshchuk.topjslibs

private val SCRIPT_SRC = """<script[^>]+?src=["'](?:[^"']+/)?([^"']+?)["']""".toRegex()

class PageJsSources(private val fetchHtml: () -> String) {

    fun get(): Set<String> {
        val html = fetchHtml()
        val matchResults = SCRIPT_SRC.findAll(html)

        return matchResults
            .filter { matchResult -> matchResult.groups.size > 1 }
            .map { matchResult -> matchResult.groups[1]?.value!! }
            .toSet()
    }
}