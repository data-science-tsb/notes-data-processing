package com.psicoder.spark.udemy.util

object FileLoader {

  def resolveFile(args: Array[String], dataLocation: String): String = {
    if (args.isDefinedAt(2)) {
      args(2)
    } else {
      s"src/main/resources/data/$dataLocation"
    }
  }
}
