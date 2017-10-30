package com.psicoder.spark.udemy

import org.apache.spark.{SparkConf, SparkContext}

object FriendsByAge {

  def main(args: Array[String]): Unit = {
    val sc = createSparkContext()

    val friendsFile = "src/main/resources/data/fakefriends.csv"

    val friendsByAge = sc.textFile(friendsFile)
      .map(_.split(",")(2))
      .countByValue()

    friendsByAge.toSeq.sortBy(_._1).foreach(println)
  }

  def createSparkContext(): SparkContext = {
    val conf = new SparkConf()
    conf.setMaster("local")
    conf.setAppName("Friends By Age")
    val sc = new SparkContext(conf)

    return sc
  }
}