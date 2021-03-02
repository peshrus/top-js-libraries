import re

SCRIPT_SRC = re.compile("""<script[^>]+?src=["']([^"']+/)?([^"']+?)["']""")


class PageJsSources:

    def __init__(self, fetch_html=lambda: ""):
        self.fetch_html = fetch_html

    def get(self) -> set[str]:
        html = self.fetch_html()
        match_results = SCRIPT_SRC.findall(html)

        return set([regex_groups[1] for regex_groups in match_results if
                    len(regex_groups) > 1])
