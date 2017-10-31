package com.psicoder.spark.udemy

import com.psicoder.spark.udemy.util.{ContextLoader, FileLoader}

object PopularSuperhero {

  def main(args: Array[String]): Unit = {
    val sc = ContextLoader.resolveSparkContext(args, "PopularSuperhero")
    val graphFile = sc.textFile(FileLoader.resolveFile(args, "Marvel-Graph.txt"))
    val namesFile = sc.textFile(FileLoader.resolveFile(args, "Marvel-Names.txt"))

    val namesRDD = namesFile.map(parseNames)

    val heroCoccurence = graphFile
      .map(countCoOccurences)
      .reduceByKey(_+_)
      .map(_.swap)
      .sortByKey(false)

    val mostPopular = heroCoccurence.first()
    val mostPopularName = namesRDD.lookup(mostPopular._2)(0)

    println(s"The most popular superhero is $mostPopularName with ${mostPopular._1} co-appearances")
  }

  def countCoOccurences(line: String): (Int, Int) = {
    val fields = line.split(" ")
    (fields(0).toInt, fields.length - 1)
  }

  def parseNames(line: String): (Int, String) = {
    val fields = line.split("\"")

    val id = fields(0).trim().toInt
    val name = if (fields.isDefinedAt(1)) fields(1) else  "Unknown"

    (id, name)
  }
}
