# Livy Basics

## Running a JAR

```json
POST /batches
{
  "file": "hdfs://xxxx.com:/user/admin/spark-scala-poc-assembly-1.0.jar",
  "className": "com.kwl2.poc.sparkscala.Main",
  "args": ["hdfs://xxxx.com:/user/admin/shakespeare.txt", "hdfs://xxxx.com:/user/livy/shakespeareWordCount"]
}
```
