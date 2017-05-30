# Hadoop
All things Hadoop-related.

## Setup
- Ansible Script for EC2

Resources: 
- [Hadoop: The Definitive Guide - O'Reilly Media](http://shop.oreilly.com/product/0636920033448.do)
- [Udemy: The Hadoop Ecosystem Masterclass](https://www.udemy.com/learn-big-data-the-hadoop-ecosystem-masterclass/)
- [Apache Ambari](https://cwiki.apache.org/confluence/display/AMBARI/Quick+Start+Guide)
- [HDP](https://hortonworks.com/products/data-center/hdp/)

## MapReduce Design Patterns
- Numerical Summarization
- Inverted Index Summarization
- Filtering
- Bloom Filter
- Top N
- Distinct
- Structured to Hierarchical
- Partitioning
- Binning
- Total Order Sorting
- Shuffling
- Reduce Side Join
- Replicated Join
- Composite Join
- Cartesian Product
- Job Chaining
- Chain Folding
- Job Merging
- Input and Output
- Generalizing Data
- External Output
- External Input
- Partition Pruning


Resources: [MapReduce Design Patterns - O'Reilly Media](http://shop.oreilly.com/product/0636920025122.do)

## Integration
- Spring for Hadoop
- Spring Cloud Dataflow
- Spring Integration

## Cluster Dashboard
- Zeppelin
- AMBARI

## File System
- HDFS
- AWS S3

## Distributed Programming
- MapReduce2 - batch processing
- Spark - alternative to MR2, mainly memory-based and simpler dev API, has DAGs
- Spark Streaming - windowed processing, continuously runs the app,  works with microbatches
- Pig - scripting layer on top of MR2 or Tez
- Ignite
- Storm - compared to spark this is a true realtime processing instead of microbatches
- Tez - used by Pig and Hive, runs everything in a single Job optimized DAG, runs on YARN, probably wont use this directly

## Storage
- HBase - OLTP storing data on HDFS

## Query Engines
- Hive - SQL facade for Hadoop
- Drill - SQL Engine for Files, Hive, RDS, Mongo...etc
- Phoenix - SQL engine for HBase
- MRQL

## Data Ingestion
- Sqoop - SQL+Hadoop, imports/exports RDBMS data into HDFS/Hive
- Flume - data sink
- NiFi

## Serializing
- Avro - data serialization and exchange, schema in payload, forward-backward data compatibility
- Parquet - columnar file format

## Scheduling / DataFlow
- Spring Cloud Dataflow
- Oozie - job coordination, workflow, decision, error handling
- LinkedIn Azkaban
- Falcon
- Schedoscope

## Service Programming
- Thrift - small JDBC server for Hive

## Messaging
- Kafka

## Indexing
- Solr
- Mahout

## Cluster Management
- YARN
- ZooKeeper
- Curator
- LinkedIn Norbert
- Mesos

## Machine Learning
- Mahout
- SystemML

## Security
- Sentry
- Knox Gateway
- Ranger

