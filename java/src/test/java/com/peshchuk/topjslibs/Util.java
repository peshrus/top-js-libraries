package com.peshchuk.topjslibs;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Util {

  private static final Logger LOGGER = Logger.getLogger(Util.class.getName());

  private Util() {
    throw new IllegalStateException("Cannot be initialized");
  }

  public static String getHtml(final String fileName) {
    try {
      final var buildDir = Path.of(Util.class.getResource("/").toURI()).toString();
      final var path = Paths.get(buildDir, "../../../../../html", fileName);
      return Files.readString(path);
    } catch (final IOException | URISyntaxException e) {
      LOGGER.log(Level.SEVERE, "Cannot get HTML", e);
      return "";
    }
  }
}
