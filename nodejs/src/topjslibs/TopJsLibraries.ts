import {GoogleSearchResult} from "./GoogleSearchResult";
import {PageJsSources} from "./PageJsSources";

export class TopJsLibraries {

  private static readonly SEARCH_URL = "https://www.google.com/search?q=";

  private readonly linksLimit: number;
  private readonly topLimit: number;
  private readonly fetchHtml: (url: string) => Promise<string>;

  constructor(readonly linksLimitValue: number,
              readonly topLimitValue: number,
              readonly fetchHtmlLambda: (url: string) => Promise<string>) {
    this.linksLimit = linksLimitValue;
    this.topLimit = topLimitValue;
    this.fetchHtml = fetchHtmlLambda;
  }

  public async count(searchStr: string): Promise<Array<[string, number]>> {
    const fetchSearchResultHtml = () => this.fetchHtml(TopJsLibraries.SEARCH_URL + searchStr);
    const links = await new GoogleSearchResult(this.linksLimit, fetchSearchResultHtml).getLinks();
    const pageJsSources = new Array<Promise<Set<string>>>();

    for (const link of links) {
      pageJsSources.push(this.getPageJsSources(link));
    }

    const jsCount = new Map<string, number>();
    const pageJsSourcesResults = await Promise.allSettled(pageJsSources);

    for (const pageJsSourcesResult of pageJsSourcesResults) {
      if (pageJsSourcesResult.status === "rejected") {
        continue;
      }

      const jsSources = (pageJsSourcesResult as PromiseFulfilledResult<Set<string>>).value;

      for (const jsSource of jsSources) {
        let counter = 0;

        if (jsCount.has(jsSource)) {
          counter = jsCount.get(jsSource)!;
        }

        jsCount.set(jsSource, counter + 1);
      }
    }

    const sortedJsCount = new Array<[string, number]>();
    for (const entry of jsCount.entries()) {
      sortedJsCount.push(entry);
    }

    sortedJsCount.sort((a, b) => (a[1] > b[1] || (a[1] === b[1] && a[0] < b[0])) ? -1 : 1);

    return sortedJsCount.slice(0, this.topLimit);
  }

  private async getPageJsSources(url: string): Promise<Set<string>> {
    return new PageJsSources(() => this.fetchHtml(url)).get();
  }
}
