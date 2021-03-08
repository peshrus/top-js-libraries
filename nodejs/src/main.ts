import bent from "bent";
import "log-timestamp";
import {TopJsLibraries} from "./topjslibs/TopJsLibraries";

function main(args: string[]) {
  if (args.length === 0) {
    console.log("Specify the Google search query, please");
    process.exit(2);
  }

  const searchStr = args[0];
  console.log(`Query: ${searchStr}`);

  console.log("Start");

  const topJsLibraries = new TopJsLibraries(5, 5, fetchHtml);
  topJsLibraries.count(searchStr).then((count) => {
    console.log("Finish");

    for (const entry of count) {
      console.log(`[${entry[1]}] ${entry[0]}`);
    }
  });
}

async function fetchHtml(url: string): Promise<string> {
  const get = bent("string", "GET", {
    "Referer": "http://www.google.com",
    "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:84.0) Gecko/20100101 Firefox/84.0",
  });

  return await get(url);
}

if (require.main === module) {
  main(process.argv.slice(2));
}
