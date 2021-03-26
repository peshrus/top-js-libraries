package com.peshchuk.topjslibs

import scala.collection.immutable.ListMap
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}
import scala.util.Try

class TopJsLibraries(private val linksLimit: Int,
                     private val topLimit: Int,
                     private val fetchHtml: String => String) {

  def count(searchStr: String): Map[String, Int] = {
    val fetchSearchResultHtml = () => fetchHtml(TopJsLibraries.SEARCH_URL + searchStr)
    val links = new GoogleSearchResult(linksLimit, fetchSearchResultHtml).getLinks
    val pagesJsSources = for (link <- links) yield Future {
      new PageJsSources(() => fetchHtml(link)).get
    }
    val jsCount = Future.sequence(pagesJsSources)
      .flatMap(jsSourceSets => Future {
        ListMap.from(jsSourceSets
          .flatten
          .groupBy(identity).view.mapValues(_.size)
          .toSeq
          .sortWith((x, y) => x._2 > y._2 || (x._2 == y._2 && x._1 < y._1))
          .slice(from = 0, until = topLimit))
      })

    Await.result(jsCount, Duration.Inf)

    jsCount
      .value
      .getOrElse(Try(ListMap[String, Int]()))
      .getOrElse(ListMap())
  }
}

object TopJsLibraries {
  private final val SEARCH_URL = "https://www.google.com/search?q="
}