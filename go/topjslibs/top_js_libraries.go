package topjslibs

import (
	"fmt"
	"sort"
	"sync"
)

const SearchUrl = "https://www.google.com/search?q="

type JsNum struct {
	Js  string
	Num int
}

func (jsNum JsNum) String() string {
	return fmt.Sprintf("%s: %d", jsNum.Js, jsNum.Num)
}

type TopJsNumList []JsNum

func (t TopJsNumList) Len() int {
	return len(t)
}

func (t TopJsNumList) Swap(i, j int) {
	t[i], t[j] = t[j], t[i]
}

func (t TopJsNumList) Less(i, j int) bool {
	return t[i].Num > t[j].Num || (t[i].Num == t[j].Num && t[i].Js < t[j].Js)
}

type TopJsLibraries struct {
	LinksLimit int
	TopLimit   int
	FetchHtml  func(url string) string
}

func (topJsLibraries *TopJsLibraries) Count(searchStr string) TopJsNumList {
	fetchGoogleSearchResultHtml := func() string { return topJsLibraries.FetchHtml(SearchUrl + searchStr) }
	googleSearchResult := GoogleSearchResult{linksLimit: topJsLibraries.LinksLimit, fetchHtml: fetchGoogleSearchResultHtml}
	links := googleSearchResult.getLinks()
	jsSources := make(chan string)
	var wg sync.WaitGroup

	for _, link := range links {
		wg.Add(1)

		url := link
		fetchPageHtml := func() string { return topJsLibraries.FetchHtml(url) }
		pageJsSources := PageJsSources{fetchHtml: fetchPageHtml}

		go getPageJsSources(&pageJsSources, jsSources, &wg)
	}

	preResult := make(map[string]int)
	go func() {
		for jsSrc := range jsSources {
			preResult[jsSrc]++
		}
	}()

	wg.Wait()
	close(jsSources)

	result := make(TopJsNumList, 0, len(preResult))
	for js := range preResult {
		result = append(result, JsNum{js, preResult[js]})
	}
	sort.Sort(result)

	return result[:topJsLibraries.TopLimit]
}

func getPageJsSources(pageJsSources *PageJsSources, jsSources chan string, wg *sync.WaitGroup) {
	defer wg.Done()

	for _, jsSrc := range pageJsSources.get() {
		jsSources <- jsSrc
	}
}
