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
```scala
//scala
def loadMovieNames(): Map[Int, String] = {
    sc.textFile("ml-100k/u.item").map(line => { 
        val fields = line.split('|')
        (fields(0).toInt, fields(1))
    }).collect().map(x => x._1 -> x._2).toMap
}

//broadcast variable
val nameDict = sc.broadcast(loadMovieNames())

//accessing broadcast variable
val sortedMoviesWithNames = sortedMovies.map(countMovie => (nameDict.value(countMovie._2), countMovie._1) )
```
```python
#python
def loadMovieNames():
    movieNames = {}
    for line in sc.textFile("ml-100k/u.item").collect():
        fields = line.split('|')
        movieNames[int(fields[0])] = fields[1]
    return movieNames
    
#broadcast variable
nameDict = sc.broadcast(loadMovieNames())

#accessing broadcast variable
sortedMoviesWithNames = sortedMovies.map(lambda countMovie : (nameDict.value[countMovie[1]], countMovie[0]))
```
- Accumulators
```scala
//scala
```
```python
#python
#create an accumulator
hitCounter = sc.accumulator(0)
#increment
hitCounter.add(1)
```
## MLLib 

## GraphX
