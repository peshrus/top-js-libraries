use crate::top_js_libs::test_util::get_html_from;
use crate::top_js_libs::top_js_libraries::TopJsLibraries;
use futures::executor::block_on;

#[test]
fn count() {
    // Arrange
    let top_js_libraries = TopJsLibraries::new(5, 5, Box::from(fetch_html));
    let mut expected = Vec::new();
    expected.push(("base.min;v35893530.js".to_string(), 2));
    expected.push(("modules.min;v35893530.js".to_string(), 2));
    expected.push(("webtk.min;v35893530.js".to_string(), 2));
    expected.push(("webtrekk_cookieControl.min;v35893530.js".to_string(), 2));
    expected.push(("application-0d7a7bcfe504533ad327.js".to_string(), 1));

    // Act
    let actual = block_on(top_js_libraries.count("test"));

    // Assert
    assert_eq!(expected, actual);
}

fn fetch_html(url: &str) -> String {
    return match url {
        "https://www.google.com/search?q=test" => get_html_from("google.com.html"),

        "https://www.test.de/" => get_html_from("test.de.html"),
        "https://www.test.de/shop/test-hefte/" => get_html_from("test-hefte.html"),
        "https://www.test.de/thema/" => get_html_from("thema.html"),
        "https://www.oekotest.de/" => get_html_from("oekotest.de.html"),
        "https://de.wikipedia.org/wiki/Test_(Zeitschrift)" => get_html_from("zeitschrift.html"),

        _ => "".to_string()
    }
}