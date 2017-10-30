package com.psicoder.spark.udemy

import com.psicoder.spark.udemy.util.{ContextLoader, FileLoader}

object MinTemperatures {

  case class Temp(location: String, timeStamp: Long, tempType: String, temperature: Float)

  def main(args: Array[String]): Unit = {
    val sc = ContextLoader.resolveSparkContext(args, "MinTemperatures")
    val file = sc.textFile(FileLoader.resolveFile(args, "1800.csv"))
      .map(convertTextToTemp)
      .filter(isTMax)

    file.take(10).foreach(println)
  }

  def isTMax(temp: Temp): Boolean = {
    temp.tempType == "TMAX"
  }
  def convertTextToTemp(text: String): Temp = {
    val fields = text.split(",")
    Temp(fields(0), fields(1).toLong, fields(2), fields(3).toFloat)
  }

}
