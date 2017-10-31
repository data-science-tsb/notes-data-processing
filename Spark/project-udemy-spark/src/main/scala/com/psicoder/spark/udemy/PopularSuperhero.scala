package com.psicoder.spark.udemy

import com.psicoder.spark.udemy.util.{ContextLoader, FileLoader}

object PopularSuperhero {

  def main(args: Array[String]): Unit = {
    val sc = ContextLoader.resolveSparkContext(args, "PopularSuperhero")
    val graphFile = sc.textFile(FileLoader.resolveFile(args, "Marvel-Graph.txt"))
    val namesFile = sc.textFile(FileLoader.resolveFile(args, "Marvel-Names.txt"))

    println("Sample Graph:")
    graphFile.take(10).foreach(println)

    println("Sample Names:")
    namesFile.take(10).foreach(println)
  }
}
