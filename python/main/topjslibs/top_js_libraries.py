import asyncio
from collections import OrderedDict

from topjslibs.google_search_result import GoogleSearchResult
from topjslibs.page_js_sources import PageJsSources

SEARCH_URL = "https://www.google.com/search?q="


class TopJsLibraries:

    def __init__(self, links_limit, top_limit, fetch_html=lambda url: ""):
        self.links_limit = links_limit
        self.top_limit = top_limit
        self.fetch_html = fetch_html

    async def __get_page_js_sources(self, url) -> set[str]:
        return PageJsSources(fetch_html=lambda: self.fetch_html(url)).get()

    async def count(self, search_str) -> OrderedDict[dict[str, int]]:
        links = GoogleSearchResult(links_limit=self.links_limit,
                                   fetch_html=lambda: self.fetch_html(
                                           SEARCH_URL + search_str)).get_links()
        get_page_js_sources_tasks = [
            asyncio.create_task(self.__get_page_js_sources(link)) for link in
            links]

        pages_js_sources = await asyncio.gather(*get_page_js_sources_tasks)
        all_js_sources = [js for sublist in pages_js_sources for js in sublist]
        js_count = {}

        for js in all_js_sources:
            js_count.setdefault(js, 0)
            js_count[js] += 1

        js_count_sorted = sorted(js_count.items(),
                                 key=lambda item: (-item[1], item[0]))

        return OrderedDict(js_count_sorted[:self.top_limit])
