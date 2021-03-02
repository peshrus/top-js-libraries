def get_html_from(file_name) -> str:
    with open(f"../../html/{file_name}") as file:
        return file.read()
