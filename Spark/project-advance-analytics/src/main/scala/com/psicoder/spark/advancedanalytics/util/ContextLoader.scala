package com.psicoder.spark.advancedanalytics.util

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SparkSession

object ContextLoader {

  def sparkSession(args: Array[String], appName: String): SparkSession = {

    val master = if (args.isDefinedAt(0)) args(0) else "local[*]"
    val finalAppName = if (args.isDefinedAt(1)) args(1) else appName

    SparkSession
      .builder()
      .appName(finalAppName)
      .master(master)
      .getOrCreate()
  }

  def sparkContext(args: Array[String], appName: String): SparkContext = {
    val conf = new SparkConf()

    if (args.isDefinedAt(0)) {
      conf.setMaster(args(0))
    } else {
      conf.setMaster("local")
    }

    if (args.isDefinedAt(1)) {
      conf.setAppName(args(1))
    } else {
      conf.setAppName(appName)
    }

    new SparkContext(conf)
  }
}
