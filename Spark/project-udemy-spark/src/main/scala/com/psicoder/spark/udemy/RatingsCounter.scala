package com.psicoder.spark.udemy

import org.apache.spark.{SparkConf, SparkContext}

/**
  * section 2: lecture 9
  * https://www.udemy.com/taming-big-data-with-apache-spark-hands-on/learn/v4/t/lecture/3709156?start=0
  */
object RatingsCounter {

  def main(args: Array[String]): Unit = {
    val sc = createSparkContext()

    val mappedRatings = sc.textFile("src/main/resources/data/ml-100k/u.data")

    val result = mappedRatings
      .map(_.split("")(2))
      .countByValue()

    result.toSeq.sortBy(_._1).foreach(println)
  }

  def createSparkContext(): SparkContext = {
    val conf = new SparkConf()
    conf.setMaster("local")
    conf.setAppName("Ratings Counter")
    val sc = new SparkContext(conf)

    return sc
  }

}
