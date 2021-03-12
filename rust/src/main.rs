use std::borrow::Borrow;
use std::env;

use futures::executor::block_on;
use hyper::{Body, Client, Request};
use hyper::body::HttpBody as _;
use hyper_tls::HttpsConnector;
use log::{info, warn};

use top_js_libs_lib::top_js_libs::top_js_libraries::TopJsLibraries;

#[tokio::main]
async fn main() {
    let args: Vec<String> = env::args().collect();

    if args.len() < 2 {
        panic!("Specify the Google search query, please");
    }

    env_logger::init();

    let search_str = &args[1];
    info!("Query: {}", search_str);

    info!("Start");

    let top_js_libraries = TopJsLibraries::new(5, 5, Box::from(fetch_html));
    let count = top_js_libraries.count(search_str).await;

    info!("Finish");

    for cnt in count {
        println!("[{}] {}", cnt.1, cnt.0);
    }
}

fn fetch_html(url: &str) -> String {
    let https = HttpsConnector::new();
    let client = Client::builder().build::<_, hyper::Body>(https);
    let uri: String = url.parse().unwrap();
    let req = Request::builder()
        .uri(uri)
        .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:84.0) Gecko/20100101 Firefox/84.0")
        .header("Referer", "http://www.google.com")
        .body(Body::from(""))
        .unwrap();
    let mut resp = block_on(client.request(req)).unwrap();

    if !(resp.status().is_success() || resp.status().is_redirection()) {
        warn!("HTTP Status {}: {}", resp.status(), url);
        return "".to_string();
    }

    let mut result = String::new();

    while let Some(chunk) = block_on(resp.body_mut().data()) {
        result.push_str(String::from_utf8_lossy(&chunk.unwrap()).borrow());
    }

    result
}