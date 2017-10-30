package com.psicoder.spark.udemy

import org.apache.spark.{SparkConf, SparkContext}

/**
  * calculate the average number of friends for age
  * section 2: lecture 10
  * https://www.udemy.com/taming-big-data-with-apache-spark-hands-on/learn/v4/t/lecture/3710440?start=0
  */
object FriendsByAge {

  def main(args: Array[String]): Unit = {

    val sc = createSparkContext()

    val friendsFile = "src/main/resources/data/fakefriends.csv"

    val friendsByAge = sc.textFile(friendsFile)
      .map(parseLine)
      .mapValues(x => (x, 1))
      .reduceByKey(computeAverage)
      .mapValues(v => v._1 / v._2)

    friendsByAge.collect().sortBy(_._1).foreach(v => {
      val age = v._1
      val averageFriends = v._2

      println(s"The average friends of age $age is $averageFriends")
    })
  }

  def computeAverage(a:(Int, Int), b:(Int, Int)): (Int, Int) = {
    (a._1 + b._1, a._2 + b._2)
  }

  def parseLine(line: String): (Int, Int) = {
    val fields = line.split(",")

    val age = fields(2).toInt
    val numFriends = fields(3).toInt

    (age, numFriends)
  }

  def createSparkContext(): SparkContext = {
    val conf = new SparkConf()
    conf.setMaster("local")
    conf.setAppName("Friends By Age")
    val sc = new SparkContext(conf)

    return sc
  }
}