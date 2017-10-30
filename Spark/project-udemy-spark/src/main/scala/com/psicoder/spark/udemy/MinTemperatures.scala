package com.psicoder.spark.udemy

import com.psicoder.spark.udemy.util.{ContextLoader, FileLoader}
import Math.min

object MinTemperatures {

  case class Temp(stationID: String, timestamp: Long, entryType: String, temperature: Float)

  /**
    *
    * @param args (master, appName, inputFile)
    */
  def main(args: Array[String]): Unit = {
    val sc = ContextLoader.resolveSparkContext(args, "MinTemperatures")
    val file = sc.textFile(FileLoader.resolveFile(args, "1800.csv"))

    val minTemps = file
      .map(parseTemp)
      .filter(isTMin)
      .map(t => (t.stationID, t.temperature))
      .reduceByKey(min)

    minTemps.collect().foreach(println)
  }

  def isTMin(temp: Temp): Boolean = temp.entryType == "TMIN"

  def parseTemp(text: String) = {
    val fields = text.split(",")
    Temp(fields(0), fields(1).toLong, fields(2), fields(3).toFloat)
  }

}
