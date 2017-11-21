package com.psicoder.spark.udemy

import java.lang.Math.addExact

import com.psicoder.spark.udemy.util.{ContextLoader, FileLoader}
import org.apache.spark.rdd.RDD

object WordCount {

  def main(args: Array[String]): Unit = {
    val sc = ContextLoader.resolveSparkContext(args, "WordCount")
    val file = sc.textFile(FileLoader.resolveFile(args, "Book.txt"))

    val wordCounts = count(file)

    val wordCountsSorted = wordCounts
      .map(_.swap)
      .sortByKey()

    wordCountsSorted
      .collect()
      .foreach { case (count, word) => println(s"$word : $count") }
  }

  def count(lines: RDD[String]): RDD[(String, Int)] = {
    lines
      .flatMap(normalizeWords)
      .map((_, 1))
      .reduceByKey(addExact)
  }

  def normalizeWords(text: String) = """\W""".r.split(text.toLowerCase)

}
