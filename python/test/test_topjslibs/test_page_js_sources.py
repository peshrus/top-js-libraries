import unittest

from test_topjslibs.util import get_html_from
from topjslibs.page_js_sources import PageJsSources


class Test(unittest.TestCase):

    def test_get(self):
        # Arrange
        expected = {
            "base.min;v35620016.js",
            "modules.min;v35620016.js",
            "webtrekk_cookieControl.min;v35620016.js",
            "webtk.min;v35620016.js"
        }
        page_js_sources = PageJsSources(
            fetch_html=lambda: get_html_from("test.de.html"))

        # Act
        actual = page_js_sources.get()

        # Assert
        self.assertEqual(expected, actual)


if __name__ == "__main__":
    unittest.main()
