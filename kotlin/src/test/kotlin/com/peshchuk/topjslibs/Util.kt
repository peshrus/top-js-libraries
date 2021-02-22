package com.peshchuk.topjslibs

import java.io.IOException
import java.net.URISyntaxException
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.logging.Level
import java.util.logging.Logger

private val LOGGER = Logger.getLogger(Util::class.qualifiedName)

class Util {

    init {
        throw IllegalStateException("Cannot be initialized")
    }

    companion object {
        fun getHtml(fileName: String): String {
            return try {
                val buildDir = Path.of(Util::class.java.getResource("/").toURI()).toString()
                val path = Paths.get(buildDir, "../../../../../html", fileName)
                return Files.readString(path)
            } catch (e: IOException) {
                LOGGER.log(Level.SEVERE, "Cannot get HTML", e)
                ""
            } catch (e: URISyntaxException) {
                LOGGER.log(Level.SEVERE, "Cannot get HTML", e)
                ""
            }
        }
    }
}