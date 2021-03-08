import * as assert from "assert";
import {describe} from "mocha";
import {GoogleSearchResult} from "../../src/topjslibs/GoogleSearchResult";
import * as util from "./util";

describe("GoogleSearchResultTest", () => {
  describe("getLinks()", () => {
    it("should get the first 5 links from the Google search result", () => {
      // Arrange
      const expected = [
        "https://www.test.de/",
        "https://www.test.de/shop/test-hefte/",
        "https://www.test.de/thema/",
        "https://www.oekotest.de/",
        "https://de.wikipedia.org/wiki/Test_(Zeitschrift)",
      ];
      const fetchHtmlLambda = () => new Promise<string>((resolve) => resolve(util.getHtmlFrom("google.com.html")));

      return new GoogleSearchResult(5, fetchHtmlLambda)
      // Act
      .getLinks()
      // Assert
      .then((actual) => assert.deepStrictEqual(actual, expected));
    });
  });
});
