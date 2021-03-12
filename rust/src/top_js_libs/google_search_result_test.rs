use crate::top_js_libs::google_search_result::GoogleSearchResult;
use crate::top_js_libs::test_util::get_html_from;

#[test]
fn get_links() {
    // Arrange
    let google_search_result = GoogleSearchResult::new(5, Box::from(|| get_html_from("google.com.html")));
    let mut expected = Vec::new();
    expected.push("https://www.test.de/".to_string());
    expected.push("https://www.test.de/shop/test-hefte/".to_string());
    expected.push("https://www.test.de/thema/".to_string());
    expected.push("https://www.oekotest.de/".to_string());
    expected.push("https://de.wikipedia.org/wiki/Test_(Zeitschrift)".to_string());

    // Act
    let actual = google_search_result.get_links();

    // Assert
    assert_eq!(expected, actual);
}