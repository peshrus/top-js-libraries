use std::cmp::Ordering;
use std::collections::{HashMap, HashSet};
use std::iter::FromIterator;

use futures::future::join_all;

use crate::top_js_libs::google_search_result::GoogleSearchResult;
use crate::top_js_libs::page_js_sources::PageJsSources;

const SEARCH_URL: &str = "https://www.google.com/search?q=";

pub struct TopJsLibraries<'a> {
    links_limit: usize,
    top_limit: usize,
    fetch_html: Box<dyn Fn(&str) -> String + 'a>,
}

impl<'a> TopJsLibraries<'a> {
    pub fn new<'b>(links_limit: usize, top_limit: usize, fetch_html: Box<dyn Fn(&str) -> String + 'b>) -> TopJsLibraries {
        TopJsLibraries {
            links_limit,
            top_limit,
            fetch_html,
        }
    }

    pub async fn count(&self, search_str: &str) -> Vec<(String, usize)> {
        let links = self.get_links(search_str);
        let pre_result_map = self.get_pre_result_map(links).await;
        let mut pre_result = Vec::from_iter(pre_result_map.iter());

        pre_result.sort_by(|a, b|
            if a.1 > b.1 || (a.1 == b.1 && a.0 < b.0) {
                Ordering::Less
            } else {
                Ordering::Greater
            });

        let mut result = Vec::new();

        for a in &pre_result[..self.top_limit] {
            result.push((a.0.to_string(), *a.1));
        }

        result
    }

    fn get_links(&self, search_str: &str) -> Vec<String> {
        let mut url = SEARCH_URL.to_owned();
        url.push_str(search_str);

        let google_search_result: GoogleSearchResult = GoogleSearchResult::new(self.links_limit, Box::from(|| (self.fetch_html)(&url)));
        let links = google_search_result.get_links();

        links
    }

    async fn get_pre_result_map(&self, links: Vec<String>) -> HashMap<String, usize> {
        let mut all_page_js_sources = Vec::new();

        for link in links {
            let page_js_source = get_page_js_sources(Box::from(move || (self.fetch_html)(&link)));
            all_page_js_sources.push(Box::pin(page_js_source));
        }

        let mut pre_result_map = HashMap::new();

        for page_js_sources in join_all(all_page_js_sources).await {
            for page_js_source in page_js_sources {
                pre_result_map.entry(page_js_source.to_string()).or_insert(0 as usize);
                pre_result_map.insert(page_js_source.to_string(), pre_result_map.get(&page_js_source).unwrap() + 1);
            }
        }

        pre_result_map
    }
}

async fn get_page_js_sources<'b>(fetch_html: Box<dyn Fn() -> String + 'b>) -> HashSet<String> {
    PageJsSources::new(fetch_html).get()
}