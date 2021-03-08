import * as assert from "assert";
import {describe} from "mocha";
import {TopJsLibraries} from "../../src/topjslibs/TopJsLibraries";
import * as util from "./util";

describe("TopJsLibrariesTest", () => {
  describe("count(string)", () => {
    it("should return top 5 most popular JS libraries from the search result", () => {
      // Arrange
      const expected = new Array<[string, number]>(
          ["base.min;v35893530.js", 2],
          ["modules.min;v35893530.js", 2],
          ["webtk.min;v35893530.js", 2],
          ["webtrekk_cookieControl.min;v35893530.js", 2],
          ["application-0d7a7bcfe504533ad327.js", 1],
      );

      return new TopJsLibraries(5, 5, fetchHtml)
      // Act
      .count("test")
      // Assert
      .then((actual) => assert.deepStrictEqual(actual, expected));
    });
  });
});

function fetchHtml(url: string): Promise<string> {
  if (url === "https://www.google.com/search?q=test") {
    return new Promise<string>((resolve) => resolve(util.getHtmlFrom("google.com.html")));
  }

  if (url === "https://www.test.de/") {
    return new Promise<string>((resolve) => resolve(util.getHtmlFrom("test.de.html")));
  }
  if (url === "https://www.test.de/shop/test-hefte/") {
    return new Promise<string>((resolve) => resolve(util.getHtmlFrom("test-hefte.html")));
  }
  if (url === "https://www.test.de/thema/") {
    return new Promise<string>((resolve) => resolve(util.getHtmlFrom("thema.html")));
  }
  if (url === "https://www.oekotest.de/") {
    return new Promise<string>((resolve) => resolve(util.getHtmlFrom("oekotest.de.html")));
  }
  if (url === "https://de.wikipedia.org/wiki/Test_(Zeitschrift)") {
    return new Promise<string>((resolve) => resolve(util.getHtmlFrom("zeitschrift.html")));
  }

  return new Promise<string>((resolve) => resolve(""));
}
