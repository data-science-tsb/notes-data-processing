package com.psicoder.spark.udemy

import com.psicoder.spark.udemy.util.{ContextLoader, FileLoader}

object PopularMovies {

  def main(args: Array[String]): Unit = {
    val sc = ContextLoader.resolveSparkContext(args, "PopularMovies")
    val file = sc.textFile(FileLoader.resolveFile(args, "ml-100k/u.data"))

    val moviePopularity = file
      .map(parse)
      .reduceByKey(_+_)
      .map(_.swap)
      .sortByKey()

    moviePopularity
      .collect()
      .foreach { case(popularity, movieId) => println(s"$movieId : $popularity") }
  }

  def parse(text: String): (Int, Int) = {
    val fields = text.split("\t")
    (fields(1).toInt, 1)
  }
}
