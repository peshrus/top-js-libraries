use std::fs;
use std::path::Path;

pub fn get_html_from(file_name: &str) -> String {
    let mut path = "../html/".to_owned(); // relative to rust folder
    path.push_str(file_name);

    let mut expect = "Cannot read: ".to_owned();
    expect.push_str(file_name);

    fs::read_to_string(Path::new(&path)).expect(&expect)
}