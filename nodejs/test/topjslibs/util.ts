import * as fs from "fs";
import * as path from "path";

export function getHtmlFrom(fileName: string): string {
  try {
    return fs.readFileSync(path.resolve(__dirname, `../../../html/${fileName}`), "utf8");
  } catch (err) {
    console.log(err);
    return "";
  }
}
