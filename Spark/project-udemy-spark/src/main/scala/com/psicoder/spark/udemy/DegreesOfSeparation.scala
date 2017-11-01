package com.psicoder.spark.udemy

import com.psicoder.spark.udemy.util.{ContextLoader, FileLoader}

object DegreesOfSeparation {

  val MAX_DISTANCE = 9999

  object VisitStatus extends Enumeration {
    val NotVisited, Visiting, Visited = Value
  }

  case class HeroNode(heroId: Int, connections: Array[Int], distance: Int, visitStatus: VisitStatus.Value)

  def main(args: Array[String]): Unit = {
    val sc = ContextLoader.resolveSparkContext(args, "PopularSuperhero")
    val graphFile = sc.textFile(FileLoader.resolveFile(args, "Marvel-Graph.txt"))


    val nodes = graphFile.map(parse)
    nodes.take(10).foreach(println)
  }

  def parse(line: String): HeroNode = {
    val fields = line.split(" ")
    HeroNode(fields(0).toInt, fields.drop(1).map(_.toInt), MAX_DISTANCE, VisitStatus.NotVisited)
  }

}
