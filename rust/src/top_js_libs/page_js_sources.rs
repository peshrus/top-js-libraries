use std::collections::HashSet;

use regex::Regex;

lazy_static! {
    static ref SCRIPT_SRC: Regex = Regex::new(r#"<script[^>]+?src=["'](?:[^"']+/)?([^"']+?)["']"#).unwrap();
}

pub struct PageJsSources<'a> {
    fetch_html: Box<dyn Fn() -> String + 'a>,
}

impl<'a> PageJsSources<'a> {
    pub fn new<'b>(fetch_html: Box<dyn Fn() -> String + 'b>) -> PageJsSources {
        PageJsSources {
            fetch_html
        }
    }

    pub fn get(&self) -> HashSet<String> {
        let html = (self.fetch_html)();
        let mut result = HashSet::new();

        for capture in SCRIPT_SRC.captures_iter(&html) {
            if capture.len() < 2 {
                continue;
            }

            result.insert(capture[1].to_string());
        }

        result
    }
}