# Livy Basics

## Running a JAR

```scala
object Main {
  def main(args: Array[String]) {
    val sc = new SparkContext(new SparkConf())

    val textFile = sc.textFile(args(0))

    val counts = textFile.flatMap(line => line.split(" "))
      .map(word => (word, 1))
      .reduceByKey(_ + _)

    counts.saveAsTextFile(args(1));
  }
}

```
```json
POST /batches
{
  "file": "hdfs:///user/admin/spark-scala-poc-assembly-1.0.jar",
  "className": "com.kwl2.poc.sparkscala.Main",
  "args": ["hdfs:///user/admin/shakespeare.txt", "hdfs:///user/livy/shakespeareWordCount2"]
}
```
