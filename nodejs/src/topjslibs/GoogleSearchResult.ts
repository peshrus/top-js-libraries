export class GoogleSearchResult {

  private static readonly SEARCH_RESULT_LINK =
      new RegExp('<a href="([^"]+?)"[^>]+?data-ved="[^"]+?"[^>]+?onmousedown="[^"]+?"><br>',
          "g");

  private readonly linksLimit: number;
  private readonly fetchHtml: () => Promise<string>;

  constructor(readonly linksLimitValue: number, readonly fetchHtmlLambda: () => Promise<string>) {
    this.linksLimit = linksLimitValue;
    this.fetchHtml = fetchHtmlLambda;
  }

  public async getLinks(): Promise<string[]> {
    const html = await this.fetchHtml();
    const matchResults = html.matchAll(GoogleSearchResult.SEARCH_RESULT_LINK);
    const result = new Array<string>();

    for (const matchResult of matchResults) {
      if (matchResult.length < 2) {
        continue;
      }

      if (result.length === this.linksLimit) {
        break;
      }

      result.push(matchResult[1]);
    }

    return result;
  }
}
