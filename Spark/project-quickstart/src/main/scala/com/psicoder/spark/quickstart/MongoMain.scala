package com.psicoder.spark.quickstart

import com.mongodb.spark.sql._
import com.mongodb.spark.config._
import org.apache.spark.sql.SparkSession

object MongoMain {

  //reference:
  //https://github.com/psi-coder/notes-hadoop/blob/master/Spark/Spark%2BMongo.md
  def main(args: Array[String]) {
    val spark = SparkSession
      .builder()
        .master("local")
        .appName("Spark-Mongo")
        .getOrCreate()

    val mongoHost = "localhost:27017"
    val database = "test"

    def makeMongoURI(collection: String) = (s"mongodb://${mongoHost}/${database}.${collection}")

    val userConf = makeMongoURI("artist")
    val readConfigUsers: ReadConfig = ReadConfig(Map("uri" -> userConf))

    val readPeople = spark.read.mongo(readConfigUsers)

    readPeople.show()
  }


}
