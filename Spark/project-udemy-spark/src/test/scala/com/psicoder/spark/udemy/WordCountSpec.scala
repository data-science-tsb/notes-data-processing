package com.psicoder.spark.udemy

import com.psicoder.spark.udemy.test.UnitSpec

import org.apache.spark.{SparkConf, SparkContext}
import org.scalatest._

class WordCountSpec extends UnitSpec with BeforeAndAfter {

  var sc: SparkContext = _

  before {
    val conf = new SparkConf()
      .setMaster("local[*]")
      .setAppName("Test WordCount")

    sc = new SparkContext(conf)
  }

  after {
    if (sc != null) {
      sc.stop()
    }
  }

  "WordCount.count" should "correctly count" in {
    val words = sc.parallelize(Seq("test test humans"))

    val wordCounts: Array[(String, Int)] = WordCount.count(words).collect()

    wordCounts should equal(Array(("humans", 1), ("test", 2)))
  }
}
