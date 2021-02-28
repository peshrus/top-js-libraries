package topjslibs

import "regexp"

var searchResultLink = regexp.MustCompile(`<a href="([^"]+)"[^>]+data-ved="[^"]+"[^>]+onmousedown="[^"]+"><br>`)

type GoogleSearchResult struct {
	linksLimit int
	fetchHtml  func() string
}

func (googleSearchResult *GoogleSearchResult) getLinks() []string {
	html := googleSearchResult.fetchHtml()
	links := searchResultLink.FindAllStringSubmatch(html, -1)
	result := make([]string, 0, googleSearchResult.linksLimit)

	for _, regexGroups := range links {
		if len(regexGroups) < 2 {
			continue
		}

		result = append(result, regexGroups[1])

		if len(result) == googleSearchResult.linksLimit {
			break
		}
	}

	return result
}
