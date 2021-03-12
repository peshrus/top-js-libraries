use regex::Regex;

lazy_static! {
    static ref SEARCH_RESULT_LINK: Regex = Regex::new(r#"<a href="([^"]+?)"[^>]+?data-ved="[^"]+?"[^>]+?onmousedown="[^"]+?"><br>"#).unwrap();
}

pub struct GoogleSearchResult<'a> {
    links_limit: usize,
    fetch_html: Box<dyn Fn() -> String + 'a>,
}

impl<'a> GoogleSearchResult<'a> {
    pub fn new<'b>(links_limit: usize, fetch_html: Box<dyn Fn() -> String + 'b>) -> GoogleSearchResult {
        GoogleSearchResult {
            links_limit,
            fetch_html,
        }
    }

    pub fn get_links(&self) -> Vec<String> {
        let html = (self.fetch_html)();
        let mut result = Vec::new();

        for capture in SEARCH_RESULT_LINK.captures_iter(&html) {
            if capture.len() < 2 {
                continue;
            }

            if result.len() == self.links_limit {
                break;
            }

            result.push(capture[1].to_string());
        }

        result
    }
}