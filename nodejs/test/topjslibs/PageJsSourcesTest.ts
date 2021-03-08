import * as assert from "assert";
import {describe} from "mocha";
import {PageJsSources} from "../../src/topjslibs/PageJsSources";
import * as util from "./util";

describe("PageJsSourcesTest", () => {
  describe("get()", () => {
    it("should get all JS sources from the page", () => {
      // Arrange
      const expected = new Set<string>([
        "base.min;v35620016.js",
        "modules.min;v35620016.js",
        "webtrekk_cookieControl.min;v35620016.js",
        "webtk.min;v35620016.js",
      ]);

      return new PageJsSources(() => new Promise<string>((resolve) => resolve(util.getHtmlFrom("test.de.html"))))
      // Act
      .get()
      // Assert
      .then((actual) => assert.deepStrictEqual(actual, expected));
    });
  });
});
