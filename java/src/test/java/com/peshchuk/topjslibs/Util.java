package com.peshchuk.topjslibs;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Util {

  private static final Logger LOGGER = Logger.getLogger(Util.class.getName());

  private Util() {
    throw new IllegalStateException("Cannot be initialized");
  }

  public static String getHtml(final String fileName) {
    try {
      return Files.readString(Path.of(Util.class.getResource("/" + fileName).toURI()));
    } catch (final IOException | URISyntaxException e) {
      LOGGER.log(Level.SEVERE, "Cannot get HTML", e);
      return "";
    }
  }
}
