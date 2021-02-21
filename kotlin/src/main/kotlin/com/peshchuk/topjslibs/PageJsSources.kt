package com.peshchuk.topjslibs

private const val SCRIPT_SRC = """<script[^>]+src=["']([^"']+/)?([^"']+)["']"""

class PageJsSources(private val fetchHtml: () -> String) {

    fun getJsSources(): Set<String> {
        val html = fetchHtml()
        val matchResults = SCRIPT_SRC.toRegex().findAll(html)

        return matchResults.map { matchResult -> matchResult.groups[2]?.value!! }.toSet()
    }
}