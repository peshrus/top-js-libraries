import asyncio
import unittest
from collections import OrderedDict

from test_topjslibs.util import get_html_from
from topjslibs.top_js_libraries import TopJsLibraries


class Test(unittest.TestCase):

    async def run_async_test(self):
        # Arrange
        expected = OrderedDict({
            "base.min;v35893530.js": 2,
            "modules.min;v35893530.js": 2,
            "webtk.min;v35893530.js": 2,
            "webtrekk_cookieControl.min;v35893530.js": 2,
            "application-0d7a7bcfe504533ad327.js": 1
        })
        top_js_libraries = \
            TopJsLibraries(links_limit=5, top_limit=5, fetch_html=fetch_html)

        # Act
        actual = await top_js_libraries.count("test")

        # Assert
        self.assertEqual(expected, actual)

    def test_count(self):
        asyncio.run(self.run_async_test())


def fetch_html(url) -> str:
    if url == "https://www.google.com/search?q=test":
        return get_html_from("google.com.html")

    if url == "https://www.test.de/":
        return get_html_from("test.de.html")
    if url == "https://www.test.de/shop/test-hefte/":
        return get_html_from("test-hefte.html")
    if url == "https://www.test.de/thema/":
        return get_html_from("thema.html")
    if url == "https://www.oekotest.de/":
        return get_html_from("oekotest.de.html")
    if url == "https://de.wikipedia.org/wiki/Test_(Zeitschrift)":
        return get_html_from("zeitschrift.html")


if __name__ == "__main__":
    unittest.main()
