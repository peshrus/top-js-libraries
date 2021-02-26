package topjslibs

import (
	"testing"
)

func TestGetLinks(t *testing.T) {
	// Arrange
	googleSearchResult := GoogleSearchResult{linksLimit: 5, fetchHtml: func() string {
		return getHtmlFrom("google.com.html")
	}}
	expected := [5]string{
		"https://www.test.de/",
		"https://www.test.de/shop/test-hefte/",
		"https://www.test.de/thema/",
		"https://www.oekotest.de/",
		"https://de.wikipedia.org/wiki/Test_(Zeitschrift)",
	}

	// Act
	var actual [5]string
	copy(actual[:], googleSearchResult.getLinks())

	// Assert
	if expected != actual {
		t.Errorf("GoogleSearchResult{linksLimit: 5}.getLinks() = %s; want %s", actual, expected)
	}
}
