package com.psicoder.spark.udemy

import scala.io.Source

import com.psicoder.spark.udemy.util.{ContextLoader, FileLoader}

object PopularMovies {

  def main(args: Array[String]): Unit = {
    val sc = ContextLoader.resolveSparkContext(args, "PopularMovies")
    val file = sc.textFile(FileLoader.resolveFile(args, "ml-100k/u.data"))

    val nameMap = sc.broadcast(loadMovieNames())

    val moviePopularity = file
      .map(parse)
      .reduceByKey(_+_)
      .map(_.swap)
      .sortByKey()
      .mapValues(nameMap.value.get(_).get)

    moviePopularity
      .collect()
      .foreach { case(popularity, movieName) => println(s"$movieName : $popularity") }
  }

  def loadMovieNames(): Map[Int, String] = {
    Source.fromFile(FileLoader.dataFile("ml-100k/u.item"), "ISO-8859-1")
      .getLines()
      .map { line =>
        val fields = line.split("\\|")
        fields(0).toInt -> fields(1)
      }
      .toMap
  }

  def parse(text: String): (Int, Int) = {
    val fields = text.split("\t")

    fields(1).toInt -> 1
  }
}
