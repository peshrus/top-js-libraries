package com.peshchuk.topjslibs;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Redirect;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

  private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

  public static void main(final String[] args) {
    if (args.length <= 0) {
      throw new IllegalArgumentException("Specify the Google search query, please");
    }

    final var searchStr = args[0];
    LOGGER.info("Query: " + searchStr);

    LOGGER.info("Start");

    final var topJsLibraries = new TopJsLibraries(5, 5, Main::fetchHtml);
    final var count = topJsLibraries.count(searchStr);

    LOGGER.info("Finish");

    count.forEach((key, value) -> System.out.println("[" + value + "] " + key));
  }

  private static String fetchHtml(final String uri) {
    LOGGER.fine("Fetch: " + uri);

    final var request = HttpRequest.newBuilder()
        .uri(URI.create(uri))
        .header("User-Agent",
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:84.0) Gecko/20100101 Firefox/84.0")
        .header("Referer", "http://www.goole.com")
        .build();
    final var httpClient = HttpClient.newBuilder()
        .version(Version.HTTP_2)
        .followRedirects(Redirect.ALWAYS)
        .connectTimeout(Duration.of(1, ChronoUnit.SECONDS))
        .build();

    try {
      final var response = httpClient.send(request, BodyHandlers.ofString());

      if (response.statusCode() < 200 || response.statusCode() >= 400) {
        throw new Exception("HTTP Status " + response.statusCode() + ": " + uri);
      }

      return response.body();
    } catch (final Exception e) {
      LOGGER.log(Level.SEVERE, "Cannot fetch HTML", e);
      return "";
    }
  }
}
