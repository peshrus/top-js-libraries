use std::collections::HashSet;

use crate::top_js_libs::page_js_sources::PageJsSources;
use crate::top_js_libs::test_util::get_html_from;

#[test]
fn get() {
    // Arrange
    let page_js_sources = PageJsSources::new(Box::from(|| get_html_from("test.de.html")));
    let mut expected = HashSet::new();
    expected.insert("base.min;v35620016.js".to_string());
    expected.insert("modules.min;v35620016.js".to_string());
    expected.insert("webtrekk_cookieControl.min;v35620016.js".to_string());
    expected.insert("webtk.min;v35620016.js".to_string());

    // Act
    let actual = page_js_sources.get();

    // Assert
    assert_eq!(expected, actual);
}