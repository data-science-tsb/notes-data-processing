# Numerical Summarization

## Data Frame
```scala
val commentsXML = spark.read.textFile("stackoverflow/Comments.xml")

case class MinMaxCountTuple(min: Long, max: Long, count: Long) {
    def +(other: MinMaxCountTuple): MinMaxCountTuple = MinMaxCountTuple(other.min+min, other.max+max, other.count+count)
}


val comments = commentsXML.flatMap { str =>
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
}

val reducedComments = comments.groupByKey(_ + _)
reducedComments.show()
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
