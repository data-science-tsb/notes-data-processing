package com.psicoder.spark.udemy

import com.psicoder.spark.udemy.util.{ContextLoader, FileLoader}

object WordCount {

  def main(args: Array[String]): Unit = {
    val sc = ContextLoader.resolveSparkContext(args, "WordCount")
    val file = sc.textFile(FileLoader.resolveFile(args, "Book.txt"))

    val wordCounts = file.flatMap(normalizeWords).countByValue()

    wordCounts.toSeq.sortBy(_._2).foreach {
      case (word, count) => println(s"$word : $count")
    }
  }

  def normalizeWords(text: String) = """\W""".r.split(text.toLowerCase)

}
