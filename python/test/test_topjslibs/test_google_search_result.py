import unittest

from test_topjslibs.util import get_html_from
from topjslibs.google_search_result import GoogleSearchResult


class Test(unittest.TestCase):

    def test_get_links(self):
        # Arrange
        expected = [
            "https://www.test.de/",
            "https://www.test.de/shop/test-hefte/",
            "https://www.test.de/thema/",
            "https://www.oekotest.de/",
            "https://de.wikipedia.org/wiki/Test_(Zeitschrift)"
        ]
        google_search_result = \
            GoogleSearchResult(links_limit=5,
                               fetch_html=lambda:
                               get_html_from("google.com.html"))

        # Act
        actual = google_search_result.get_links()

        # Assert
        self.assertEqual(expected, actual)


if __name__ == "__main__":
    unittest.main()
