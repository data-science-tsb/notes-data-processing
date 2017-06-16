# Spark: Basics

## Spark Context
```scala
val conf = new SparkConf().setAppName(appName).setMaster(master)
val sc = new SparkContext(conf)
```
```python
conf = SparkConf().setAppName(appName).setMaster(master)
sc = SparkContext(conf=conf)
```

## Loading File to RDD
```scala
val item = sc.textFile("ml-100k/u.item")
```
```python
item = sc.textFile("ml-100k/u.item")
```

## RDD
- Data Frames
- Data Sets
- Caching RDDs
- Partitioning
- External Datasets

## RDD Operations 
- Actions
- Transformations

## Shared Variables
- Broadcast Variables
- Accumulators

## MLLib 

## GraphX
