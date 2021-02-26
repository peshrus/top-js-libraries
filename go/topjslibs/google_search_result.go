package topjslibs

import (
	"regexp"
)

const SearchResultLink = "<a href=\"([^\"]+)\"[^>]+data-ved=\"[^\"]+\"[^>]+onmousedown=\"[^\"]+\"><br>"

type GoogleSearchResult struct {
	linksLimit int
	fetchHtml  func() string
}

func (googleSearchResult *GoogleSearchResult) getLinks() []string {
	html := googleSearchResult.fetchHtml()
	links := regexp.MustCompile(SearchResultLink).FindAllStringSubmatch(html, -1)
	result := make([]string, 0, googleSearchResult.linksLimit)

	for i, regexGroups := range links {
		if i >= googleSearchResult.linksLimit {
			break
		}

		result = append(result, regexGroups[1])
	}

	return result
}
