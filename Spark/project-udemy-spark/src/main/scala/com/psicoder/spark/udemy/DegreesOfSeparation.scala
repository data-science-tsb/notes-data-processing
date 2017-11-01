package com.psicoder.spark.udemy

import com.psicoder.spark.udemy.util.{ContextLoader, FileLoader}
import org.apache.spark.util.LongAccumulator

/**
  * calculate the degree of separation between two nodes
  */
object DegreesOfSeparation {

  val MAX_DISTANCE = 9999
  val startHeroId = 5306 //SpiderMan
  val targetHeroId = 14 //Adam

  object VisitStatus extends Enumeration {
    val NotVisited, Visiting, Visited = Value
  }

  case class HeroNode(heroId: Int, connections: Array[Int], distance: Int, visitStatus: VisitStatus.Value)

  def main(args: Array[String]): Unit = {
    val sc = ContextLoader.resolveSparkContext(args, "PopularSuperhero")
    val graphFile = sc.textFile(FileLoader.resolveFile(args, "Marvel-Graph.txt"))

    val hitCounter = sc.longAccumulator("hitCounter")

    var iterationRDD = graphFile.map(parse)
    for (iteration <- 0 to 10) {

      val mapped = iterationRDD.flatMap(expandNode(_, hitCounter))

      //cheat code to force flatMap evaluation
      iterationRDD.count()

      if (!hitCounter.isZero) {
        //TODO: break off this loop
      }

      iterationRDD = mapped.reduceByKey(reduce)

    }
  }

  def parse(line: String): (Int, HeroNode) = {
    val fields = line.split(" ")

    val heroId = fields(0).toInt

    val (visitStatus, distance) =
      if (heroId == startHeroId) (VisitStatus.Visiting, 0)
      else (VisitStatus.NotVisited, MAX_DISTANCE)

    (heroId, HeroNode(heroId, fields.drop(1).map(_.toInt), distance, visitStatus))
  }

  def expandNode(currentNode: (Int, HeroNode), hitCounter: LongAccumulator): Seq[(Int, HeroNode)] = {
    //TODO: implement
    Array(currentNode)
  }

  def reduce(a: HeroNode, b: HeroNode): HeroNode = {
    //TODO: implement
    HeroNode(1,Array(1,2,3),MAX_DISTANCE,VisitStatus.Visited)
  }

}
