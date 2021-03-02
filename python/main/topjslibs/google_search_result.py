import re

SEARCH_RESULT_LINK = re.compile(
        """<a href="([^"]+?)"[^>]+?data-ved="[^"]+?"[^>]+?onmousedown="[^"]+?">\
<br>""")


class GoogleSearchResult:

    def __init__(self, links_limit, fetch_html=lambda: ""):
        self.links_limit = links_limit
        self.fetch_html = fetch_html

    def get_links(self) -> list[str]:
        html = self.fetch_html()
        match_results = SEARCH_RESULT_LINK.findall(html)

        return match_results[:self.links_limit]
