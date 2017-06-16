# Spark: Basics

## Spark Context
```scala
//scala
val conf = new SparkConf().setAppName(appName).setMaster(master)
val sc = new SparkContext(conf)
```
```python
#python
conf = SparkConf().setAppName(appName).setMaster(master)
sc = SparkContext(conf=conf)
```

## Loading File to RDD
```scala
//scala
val item = sc.textFile("ml-100k/u.item")
```
```python
#python
item = sc.textFile("ml-100k/u.item")
```

## RDD
- Data Frames
- Data Sets
- Caching RDDs
- Partitioning
- External Datasets

## RDD Operations
```scala
//scala
val lines = sc.textFile("ml-100k/u.data")
val ratings = lines.map(_.split("\t")(2))
val result = ratings.countByValue()

print(result)
```
```python
#python
from pyspark import SparkConf, SparkContext
import collections

lines = sc.textFile("ml-100k/u.data")
ratings = lines.map(lambda x: x.split()[2])
result = ratings.countByValue()

print result
```
- [Actions](https://spark.apache.org/docs/latest/programming-guide.html#actions)
```
reduce
collect
count
```
- [Transformations](https://spark.apache.org/docs/latest/programming-guide.html#transformations)
```
map
flatMap
filter
distinct
sample
union
intersection
subtract
cartesian
```

## Shared Variables
- Broadcast Variables
- Accumulators

## MLLib 

## GraphX
