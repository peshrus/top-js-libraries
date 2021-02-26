package topjslibs

import (
	"testing"
)

func TestCount(t *testing.T) {
	// Arrange
	expected := [5]JsNum{
		{"base.min;v35893530.js", 2},
		{"modules.min;v35893530.js", 2},
		{"webtk.min;v35893530.js", 2},
		{"webtrekk_cookieControl.min;v35893530.js", 2},
		{"application-0d7a7bcfe504533ad327.js", 1},
	}

	// Act
	topJsLibraries := TopJsLibraries{LinksLimit: 5, TopLimit: 5, FetchHtml: fetchHtml}
	var actual [5]JsNum
	copy(actual[:], topJsLibraries.Count("test"))

	// Assert
	if expected != actual {
		t.Errorf("TopJsLibraries{LinksLimit: 5, TopLimit: 5}.count(\"test\") = %s; want %s", actual, expected)
	}
}

func fetchHtml(url string) string {
	switch url {
	case "https://www.google.com/search?q=test":
		return getHtmlFrom("google.com.html")

	case "https://www.test.de/":
		return getHtmlFrom("test.de.html")
	case "https://www.test.de/shop/test-hefte/":
		return getHtmlFrom("test-hefte.html")
	case "https://www.test.de/thema/":
		return getHtmlFrom("thema.html")
	case "https://www.oekotest.de/":
		return getHtmlFrom("oekotest.de.html")
	case "https://de.wikipedia.org/wiki/Test_(Zeitschrift)":
		return getHtmlFrom("zeitschrift.html")

	default:
		return ""
	}
}
