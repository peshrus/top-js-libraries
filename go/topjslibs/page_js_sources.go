package topjslibs

import (
	"regexp"
)

const ScriptSrc = "<script[^>]+src=[\"']([^\"']+/)?([^\"']+)[\"']"

type PageJsSources struct {
	fetchHtml func() string
}

func (pageJsSources *PageJsSources) get() []string {
	html := pageJsSources.fetchHtml()
	src := regexp.MustCompile(ScriptSrc).FindAllStringSubmatch(html, -1)
	uniqueSrc := make(map[string]bool, len(src))

	for _, regexGroups := range src {
		uniqueSrc[regexGroups[2]] = true
	}

	result := make([]string, 0, len(uniqueSrc))
	for key := range uniqueSrc {
		result = append(result, key)
	}

	return result
}
