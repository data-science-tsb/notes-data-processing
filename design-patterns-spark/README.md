# Implement MapReduce Design Patterns with Spark

## Prerequisites: Download Data Source from StackExchange
```sh
//do this once: install zip extractor
sudo apt-get install p7zip-full

//download sample data and upload to hdfs after extraction
//download here: https://archive.org/download/stackexchange
wget https://archive.org/download/stackexchange/stackoverflow.com-Comments.7z
7z x stackoverflow.com-Comments.7z
hadoop fs -put Comments.xml /user/zeppelin/stackoverflow
```
