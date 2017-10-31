package com.psicoder.spark.udemy

import java.lang.Math.min

import com.psicoder.spark.udemy.util.{ContextLoader, FileLoader}

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
      .map(parse)
      .filter(_.entryType == "TMIN")
      .map(t => (t.stationID, t.temperature))
      .reduceByKey(min)

    minTemps.collect().foreach(println)
  }

  def parse(text: String) = {
    val fields = text.split(",")
    Temp(fields(0), fields(1).toLong, fields(2), fields(3).toFloat)
  }

}
