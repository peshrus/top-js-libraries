# Top JS Libraries [![Java CI with Gradle](https://github.com/peshrus/top-js-libraries/actions/workflows/gradle.yml/badge.svg)](https://github.com/peshrus/top-js-libraries/actions/workflows/gradle.yml)

The program searches for the specified string on Google, takes the first 5 results, collects all the
JS libraries used there and shows top 5 most popular ones.

# Implementations

- [Java](java/src/main/java/com/peshchuk/topjslibs)
  ```
  Mar 08, 2021 7:17:26 PM com.peshchuk.topjslibs.Main main
  INFO: Query: test
  Mar 08, 2021 7:17:26 PM com.peshchuk.topjslibs.Main main
  INFO: Start
  Mar 08, 2021 7:17:29 PM com.peshchuk.topjslibs.Main main
  INFO: Finish
  [3] base.min;v36269654.js
  [3] modules.min;v36269654.js
  [3] webtk.min;v36269654.js
  [3] webtrekk_cookieControl.min;v36269654.js
  [1] application-2325c4e1a6e3b853b8f3.js
  ```
- [Kotlin](kotlin/src/main/kotlin/com/peshchuk/topjslibs)
  ```
  Mar 08, 2021 7:15:36 PM com.peshchuk.topjslibs.MainKt main
  INFO: Query: test
  Mar 08, 2021 7:15:36 PM com.peshchuk.topjslibs.MainKt main
  INFO: Start
  Mar 08, 2021 7:15:40 PM com.peshchuk.topjslibs.MainKt main
  INFO: Finish
  [3] base.min;v36269654.js
  [3] modules.min;v36269654.js
  [3] webtk.min;v36269654.js
  [3] webtrekk_cookieControl.min;v36269654.js
  [1] application-2325c4e1a6e3b853b8f3.js
  ```
- [Go](go/topjslibs)
  ```
  2021/03/08 19:24:02.796742 Query: test
  2021/03/08 19:24:02.797789 Start
  2021/03/08 19:24:04.047517 Finish
  [3] base.min;v36269654.js
  [3] modules.min;v36269654.js
  [3] webtk.min;v36269654.js
  [3] webtrekk_cookieControl.min;v36269654.js
  [1] application-2325c4e1a6e3b853b8f3.js
  ```
- [Python](python/main/topjslibs)
  ```
  03/08/2021 07:11:43 PM Query: test
  03/08/2021 07:11:43 PM Start
  03/08/2021 07:11:47 PM Finish
  [3] base.min;v36269654.js
  [3] modules.min;v36269654.js
  [3] webtk.min;v36269654.js
  [3] webtrekk_cookieControl.min;v36269654.js
  [1] application-2325c4e1a6e3b853b8f3.js
  ```
- [Node.js (TypeScript)](nodejs/src)
  ```
  [2021-03-08T18:09:12.792Z] Query: test
  [2021-03-08T18:09:12.794Z] Start
  [2021-03-08T18:09:15.620Z] Finish
  [2021-03-08T18:09:15.620Z] [3] base.min;v36269654.js
  [2021-03-08T18:09:15.620Z] [3] modules.min;v36269654.js
  [2021-03-08T18:09:15.620Z] [3] webtk.min;v36269654.js
  [2021-03-08T18:09:15.620Z] [3] webtrekk_cookieControl.min;v36269654.js
  [2021-03-08T18:09:15.620Z] [1] application-2325c4e1a6e3b853b8f3.js
  ```
- Rust (TODO)
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