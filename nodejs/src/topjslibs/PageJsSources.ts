export class PageJsSources {

  private static readonly SCRIPT_SRC =
      new RegExp('<script[^>]+?src=["\'](?:[^"\']+/)?([^"\']+?)["\']', "g");

  private readonly fetchHtml: () => Promise<string>;

  constructor(readonly fetchHtmlLambda: () => Promise<string>) {
    this.fetchHtml = fetchHtmlLambda;
  }

  public async get(): Promise<Set<string>> {
    const html = await this.fetchHtml();
    const matchResults = html.matchAll(PageJsSources.SCRIPT_SRC);
    const result = new Set<string>();

    for (const matchResult of matchResults) {
      if (matchResult.length < 2) {
        continue;
      }

      result.add(matchResult[1]);
    }

    return result;
  }
}
