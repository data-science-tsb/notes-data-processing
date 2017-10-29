package com.psicoder.spark.udemy

import org.apache.spark.{SparkConf, SparkContext}

object WordCount {

  def main(args: Array[String]): Unit = {
    val sc = createSparkContext()

    val textFile = sc.textFile("src/main/resources/data/audioscrobbler/user_artist_data")

    val countLines = textFile.map(_ => 1).reduce(_+_)

    println("number of lines: " + countLines)
  }

  def createSparkContext(): SparkContext = {
    val conf = new SparkConf()
    conf.setMaster("local")
    conf.setAppName("Word Count")
    val sc = new SparkContext(conf)

    return sc
  }
}
