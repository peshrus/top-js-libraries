import asyncio
import logging
import re
import sys
from http import client
from http.client import HTTPS_PORT, HTTP_PORT

from topjslibs.top_js_libraries import TopJsLibraries

# noinspection PyArgumentList
logging.basicConfig(format="%(asctime)s %(message)s",
                    datefmt="%m/%d/%Y %I:%M:%S %p",
                    encoding="utf-8",
                    level=logging.INFO)

URL = re.compile(
    """(?P<protocol>http(?:s)??)://(?P<host>[^/]+?)(?P<path>/.*)""")


async def main(argv):
    if len(argv) == 0:
        logging.error("Specify the Google search query, please")
        sys.exit(2)

    search_str = argv[0]
    logging.info(f"Query: {search_str}")

    logging.info("Start")

    top_js_libraries = \
        TopJsLibraries(links_limit=5, top_limit=5, fetch_html=fetch_html)
    count = await top_js_libraries.count(search_str)

    logging.info("Finish")

    for js, cnt in count.items():
        print(f"{cnt} {js}")


def fetch_html(url) -> str:
    url_parts = URL.match(url).groupdict()
    port = HTTPS_PORT if url_parts["protocol"] == "https" else HTTP_PORT

    connection = client.HTTPSConnection(url_parts["host"], port=port)
    connection.request("GET", url_parts["path"], headers={
        "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:84.0) "
                      "Gecko/20100101 Firefox/84.0",
        "Referer": "http://www.google.com"
    })

    response = connection.getresponse()
    result = ""

    if response.status < 200 or response.status >= 400:
        logging.error(f"HTTP Status {response.status}: {url}")
    else:
        result = str(response.read())

    connection.close()

    return result


if __name__ == "__main__":
    asyncio.run(main(sys.argv[1:]))
