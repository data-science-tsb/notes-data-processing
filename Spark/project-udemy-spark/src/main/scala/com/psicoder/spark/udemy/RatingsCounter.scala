package com.psicoder.spark.udemy

import com.psicoder.spark.udemy.util.{ContextLoader, FileLoader}

/**
  * section 2: lecture 9
  * https://www.udemy.com/taming-big-data-with-apache-spark-hands-on/learn/v4/t/lecture/3709156?start=0
  */
object RatingsCounter {

  def main(args: Array[String]): Unit = {
    val sc = ContextLoader.resolveSparkContext(args, "RatingsCounter")
    val file = sc.textFile(FileLoader.resolveFile(args, "ml-100k/u.data"))

    val result = file
      .map(parse)
      .countByValue()

    result
      .toSeq
      .sortBy(_._1)
      .foreach(println)
  }

  def parse(text: String): Int = {
    val fields = text.split("\t")
    if (fields.isDefinedAt(2)) fields(2).toInt else 0
  }

}
