package com.psicoder.spark.quickstart

import org.apache.spark.sql.SparkSession

object JDBCMain {

  def main(args: Array[String]) {
    val spark = SparkSession
      .builder()
      .master("local")
      .appName("Spark-JDBC")
      .getOrCreate()

    val jdbcUsername = "spark"
    val jdbcPassword = ""
    val jdbcHostname = "localhost"
    val jdbcPort = 5432
    val jdbcDatabase ="spark"
    val jdbcUrl = s"jdbc:postgresql://${jdbcHostname}:${jdbcPort}/${jdbcDatabase}?user=${jdbcUsername}&password=${jdbcPassword}"
    val connectionProperties = new java.util.Properties()

    val dimArtist = spark.read.jdbc(jdbcUrl, "dim_artist", connectionProperties)

    dimArtist.show()
  }

}
