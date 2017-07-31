# Livy Basics

## Running a JAR

```json
POST /batches
{
  "file": "hdfs:///user/admin/spark-scala-poc-assembly-1.0.jar",
  "className": "com.kwl2.poc.sparkscala.Main",
  "args": ["hdfs:///user/admin/shakespeare.txt", "hdfs:///user/livy/shakespeareWordCount2"]
}
```
