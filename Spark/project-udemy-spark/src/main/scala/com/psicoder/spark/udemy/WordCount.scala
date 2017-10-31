package com.psicoder.spark.udemy

import java.lang.Math.addExact

import com.psicoder.spark.udemy.util.{ContextLoader, FileLoader}

object WordCount {

  def main(args: Array[String]): Unit = {
    val sc = ContextLoader.resolveSparkContext(args, "WordCount")
    val file = sc.textFile(FileLoader.resolveFile(args, "Book.txt"))

    val wordCounts = file
      .flatMap(normalizeWords)
      .map((_, 1))
      .reduceByKey(addExact)

    val wordCountsSorted = wordCounts
      .map { case(k, v) => (v, k) }
      .sortByKey()

    wordCountsSorted
      .collect()
      .foreach { case (count, word) => println(s"$word : $count") }
  }

  def normalizeWords(text: String) = """\W""".r.split(text.toLowerCase)

}
