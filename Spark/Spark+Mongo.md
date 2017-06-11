# Spark and MongoDB Connector

## Add the Connector Dependency
```
org.mongodb.spark:mongo-spark-connector_2.11:2.0.0
```

## Connect
```scala
import com.mongodb.spark._
import com.mongodb.spark.sql._
import com.mongodb.spark.config._

//need help?
//https://docs.mongodb.com/spark-connector/master/configuration/
//https://stackoverflow.com/questions/39576857/mongo-spark-connector-and-mongo-3-2-root-user-cannot-read-database

//problem with auth: CHECK THIS OUT
//https://github.com/mongodb/mongo-hadoop/wiki/Usage-with-Authentication

val mongoHost = "host.com:27017"
val mongoUsername = "user"
val mongoPassword = "pass"
val database = "kwl"
val authDatabase = "admin"

def makeMongoURI(collection: String) = (s"mongodb://${mongoUsername}:${mongoPassword}@${mongoHost}/${database}.${collection}?authSource=${authDatabase}")

val userConf = makeMongoURI("user")
val readConfigUsers: ReadConfig = ReadConfig(Map("uri" -> userConf))

val readPeople = spark.read.mongo(readConfigUsers)
```
