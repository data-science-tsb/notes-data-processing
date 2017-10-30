package com.psicoder.spark.udemy.util

import org.apache.spark.{SparkConf, SparkContext}

object ContextLoader {

  def resolveSparkContext(args: Array[String], appName: String): SparkContext = {
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
