# Numerical Summarization

## Data Frame
```scala
val commentsXML = spark.read.textFile("stackoverflow/Comments-Small.xml")

case class MinMaxCountTuple(userId: Long, min: Long, max: Long, count: Long)


val commentsMinMax = commentsXML.flatMap { str =>
    try {
        if (str.trim contains "row") {
            try {
                val s = scala.xml.XML.loadString(str.trim)
                val text = (s \ "@Text").text
                Some(MinMaxCountTuple((s \ "@UserId").text.toLong, text.size, text.size, 1))
            } catch {
                case e: Exception => None   
            }
        } else {
            None   
        }
    } catch {
        case e: MatchError => None
    }
}.groupBy("userId").agg(min("min").alias("min"), max("max").alias("max"), sum("count").alias("count"))

commentsMinMax.show()
```

## RDD
```scala
case class MinMaxCountTuple(min: Long, max: Long, count: Long) {
    def +(other: MinMaxCountTuple): MinMaxCountTuple = MinMaxCountTuple(other.min+min, other.max+max, other.count+count)
}

val commentsXML = sc.textFile("stackoverflow/Comments.xml")
val minMaxCount = commentsXML.flatMap { str =>
    try {
        if (str.trim contains "row") {
            val s = scala.xml.XML.loadString(str.trim)
            val text = (s \ "@Text").text
            Some(((s \ "@UserId").text, MinMaxCountTuple(text.size, text.size, 1) ))
        } else {
            None   
        }
    } catch {
        case e: MatchError => None
    }
}.reduceByKey(_ + _)
minMaxCount.take(10).foreach(println)
```
