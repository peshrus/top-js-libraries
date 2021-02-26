package topjslibs

import (
	"testing"
)

func TestGet(t *testing.T) {
	// Arrange
	pageJsSources := PageJsSources{func() string {
		return getHtmlFrom("test.de.html")
	}}
	expected := []string{
		"base.min;v35620016.js",
		"modules.min;v35620016.js",
		"webtrekk_cookieControl.min;v35620016.js",
		"webtk.min;v35620016.js",
	}

	// Act
	actual := pageJsSources.get()
	actualMap := make(map[string]bool)
	for _, value := range actual {
		actualMap[value] = true
	}

	// Assert
	if len(expected) != len(actual) {
		t.Errorf("PageJsSources.get() = %s; want %s", actual, expected)
	}

	for _, expectedValue := range expected {
		if _, ok := actualMap[expectedValue]; !ok {
			t.Errorf("PageJsSources.get() = %s; want %s", actual, expected)
		}
	}
}
