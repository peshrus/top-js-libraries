package com.peshchuk.topjslibs

import java.io.IOException
import java.net.URISyntaxException
import java.nio.file.{Files, Path, Paths}
import java.util.logging.{Level, Logger}

class Util {
  throw new IllegalStateException("Cannot be initialized")
}

object Util {
  private val LOGGER = Logger.getLogger(Util.getClass.getName)

  def getHtmlFrom(fileName: String): String = {
    try {
      val buildDir = Path.of(Util.getClass.getResource("/").toURI).toString
      var htmlPath = "../../../../html"

      // gradle build
      if (buildDir.endsWith("test")) {
        htmlPath = "../" + htmlPath
      }

      val path = Paths.get(buildDir, htmlPath, fileName)

      return Files.readString(path)
    } catch {
      case e: IOException => LOGGER.log(Level.SEVERE, "Cannot get HTML", e)
      case e: URISyntaxException => LOGGER.log(Level.SEVERE, "Cannot get HTML", e)
    }

    ""
  }
}
