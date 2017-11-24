package com.psicoder.spark.advancedanalytics.util

object FileLoader {

  def resolveFile(args: Array[String], dataLocation: String): String = {
    if (args.isDefinedAt(2)) {
      args(2)
    } else {
      dataFile(dataLocation)
    }
  }

  def dataFile(fileName: String): String = s"src/main/resources/data/$fileName"
}
