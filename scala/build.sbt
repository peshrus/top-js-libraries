scalaVersion := "2.13.5"

// ============================================================================

name := "scala"
organization := "com.peshchuk"
version := "1.0-SNAPSHOT"

// https://index.scala-lang.org/scala/scala-parser-combinators
libraryDependencies += "org.scalaj" %% "scalaj-http" % "2.4.2"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.6" % Test
libraryDependencies += "org.scalatestplus" %% "junit-4-13" % "3.2.6.0" % Test

// ============================================================================

// http://www.scala-sbt.org/documentation.html
