# Top JS Libraries [![Java CI with Gradle](https://github.com/peshrus/top-js-libraries/actions/workflows/gradle.yml/badge.svg)](https://github.com/peshrus/top-js-libraries/actions/workflows/gradle.yml)

The program searches for the specified string on Google, takes the first 5 results, collects all the
JS libraries used there and shows top 5 most popular ones.

# Implementations

- [Java](java/src/main/java/com/peshchuk/topjslibs)
  ```
  Mar 13, 2021 12:13:12 AM com.peshchuk.topjslibs.Main main
  INFO: Query: test
  Mar 13, 2021 12:13:12 AM com.peshchuk.topjslibs.Main main
  INFO: Start
  Mar 13, 2021 12:13:14 AM com.peshchuk.topjslibs.Main main
  INFO: Finish
  [2] load.php?lang=de&amp;modules=startup&amp;only=scripts&amp;raw=1&amp;skin=vector
  [2] wrapperMessagingWithoutDetection.js
  [1] application-2325c4e1a6e3b853b8f3.js
  [1] base.min;v37467901.js
  [1] consenter-8b7dac5389105a62af9f.js
  ```
- [Kotlin](kotlin/src/main/kotlin/com/peshchuk/topjslibs)
  ```
  Mar 13, 2021 12:13:51 AM com.peshchuk.topjslibs.MainKt main
  INFO: Query: test
  Mar 13, 2021 12:13:51 AM com.peshchuk.topjslibs.MainKt main
  INFO: Start
  Mar 13, 2021 12:13:54 AM com.peshchuk.topjslibs.MainKt main
  INFO: Finish
  [2] load.php?lang=de&amp;modules=startup&amp;only=scripts&amp;raw=1&amp;skin=vector
  [2] wrapperMessagingWithoutDetection.js
  [1] application-2325c4e1a6e3b853b8f3.js
  [1] base.min;v37467901.js
  [1] consenter-8b7dac5389105a62af9f.js
  ```
- [Go](go/topjslibs)
  ```
  2021/03/13 00:14:20.290604 Query: test
  2021/03/13 00:14:20.291650 Start
  2021/03/13 00:14:21.436489 Finish
  [2] load.php?lang=de&amp;modules=startup&amp;only=scripts&amp;raw=1&amp;skin=vector
  [2] wrapperMessagingWithoutDetection.js
  [1] application-2325c4e1a6e3b853b8f3.js
  [1] base.min;v37467901.js
  [1] consenter-8b7dac5389105a62af9f.js
  ```
- [Python](python/main/topjslibs)
  ```
  03/13/2021 12:15:33 AM Query: test
  03/13/2021 12:15:33 AM Start
  03/13/2021 12:15:35 AM Finish
  [2] load.php?lang=de&amp;modules=startup&amp;only=scripts&amp;raw=1&amp;skin=vector
  [2] wrapperMessagingWithoutDetection.js
  [1] application-2325c4e1a6e3b853b8f3.js
  [1] base.min;v37467901.js
  [1] consenter-8b7dac5389105a62af9f.js
  ```
- [Node.js (TypeScript)](nodejs/src)
  ```
  [2021-03-12T23:17:10.405Z] Query: test
  [2021-03-12T23:17:10.410Z] Start
  [2021-03-12T23:17:11.598Z] Finish
  [2021-03-12T23:17:11.598Z] [2] load.php?lang=de&amp;modules=startup&amp;only=scripts&amp;raw=1&amp;skin=vector
  [2021-03-12T23:17:11.599Z] [2] wrapperMessagingWithoutDetection.js
  [2021-03-12T23:17:11.600Z] [1] application-2325c4e1a6e3b853b8f3.js
  [2021-03-12T23:17:11.600Z] [1] base.min;v37467901.js
  [2021-03-12T23:17:11.601Z] [1] consenter-8b7dac5389105a62af9f.js
  ```
- [Rust](rust/src/top_js_libs)
  ```
  [2021-03-12T23:12:33Z INFO  top_js_libs_bin] Query: test
  [2021-03-12T23:12:33Z INFO  top_js_libs_bin] Start
  [2021-03-12T23:12:35Z INFO  top_js_libs_bin] Finish
  [2] load.php?lang=de&amp;modules=startup&amp;only=scripts&amp;raw=1&amp;skin=vector
  [2] wrapperMessagingWithoutDetection.js
  [1] application-2325c4e1a6e3b853b8f3.js
  [1] base.min;v37467901.js
  [1] consenter-8b7dac5389105a62af9f.js
  ```
- Scala (TODO)
- Ruby (MAYBE)
- Haskell (MAYBE)
- Elixir (MAYBE)
- Swift (MAYBE)
- PHP (MAYBE)
- C# (MAYBE)
- C++ (MAYBE)
- C (MAYBE)
- Perl (MAYBE)