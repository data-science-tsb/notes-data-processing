package com.psicoder.spark.udemy

import com.psicoder.spark.udemy.util.{ContextLoader, FileLoader}

/**
  * calculate the average number of friends for age
  * section 2: lecture 10
  * https://www.udemy.com/taming-big-data-with-apache-spark-hands-on/learn/v4/t/lecture/3710440?start=0
  */
object FriendsByAge {

  def main(args: Array[String]): Unit = {
    val sc = ContextLoader.resolveSparkContext(args, "FriendsByAge")
    val file = sc.textFile(FileLoader.resolveFile(args, "fakefriends.csv"))

    val friendsByAge = file
      .map(parse)
      .mapValues((_, 1))
      .reduceByKey(friendCountSummation)
      .mapValues { case(sumFriends, sumPopulation) => sumFriends / sumPopulation }

    friendsByAge
      .collect()
      .sortBy(_._1)
      .foreach { case(age, averageFriends) => println(s"The average friends of age $age is $averageFriends") }
  }

  def friendCountSummation(a:(Int, Int), b:(Int, Int)): (Int, Int) = (a._1 + b._1, a._2 + b._2)

  def parse(line: String): (Int, Int) = {
    val fields = line.split(",")

    val age = fields(2).toInt
    val numFriends = fields(3).toInt

    (age, numFriends)
  }
}