package topjslibs

import "regexp"

var scriptSrc = regexp.MustCompile(`<script[^>]+?src=["'](?:[^"']+/)?([^"']+?)["']`)

type PageJsSources struct {
	fetchHtml func() string
}

func (pageJsSources *PageJsSources) get() []string {
	html := pageJsSources.fetchHtml()
	jsSources := scriptSrc.FindAllStringSubmatch(html, -1)
	uniqueJsSources := make(map[string]bool, len(jsSources))

	for _, regexGroups := range jsSources {
		if len(regexGroups) < 2 {
			continue
		}

		uniqueJsSources[regexGroups[1]] = true
	}

	result := make([]string, 0, len(uniqueJsSources))
	for key := range uniqueJsSources {
		result = append(result, key)
	}

	return result
}
