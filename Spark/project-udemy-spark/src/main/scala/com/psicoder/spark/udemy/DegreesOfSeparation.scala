package com.psicoder.spark.udemy

import com.psicoder.spark.udemy.util.{ContextLoader, FileLoader}
import org.apache.spark.util.LongAccumulator

/**
  * calculate the degree of separation between two nodes
  */
object DegreesOfSeparation {

  object VisitStatus extends Enumeration {
    val NotVisited, Visiting, Visited = Value
  }

  case class HeroNode(connections: Array[Int], distance: Int, visitStatus: VisitStatus.Value) {
    override def toString() = s"HeroNode(Array(${connections.mkString(",")}), $distance, VisitStatus.$visitStatus)"
  }

  def main(args: Array[String]): Unit = {
    val MAX_DISTANCE = 9999
    val startHeroId = 5306 //SpiderMan
    val targetHeroId = 14 //Adam

    val sc = ContextLoader.resolveSparkContext(args, "PopularSuperhero")
    val graphFile = sc.textFile(FileLoader.resolveFile(args, "Marvel-Graph.txt"))

    val hitCounter = sc.longAccumulator("hitCounter")

    var (found, distance, hits) = (false, MAX_DISTANCE, 0L)
    var iterationRDD = graphFile.map(parse(_, startHeroId, MAX_DISTANCE))
    for (iteration <- 1 to 10) {
      if (!found) {
        val mapped = iterationRDD.flatMap(expandNode(_, hitCounter, targetHeroId))

        //cheat code to force flatMap evaluation
        println(s"Iteration #$iteration: Processing ${mapped.count()} values.")

        val isHit = !hitCounter.isZero
        if (isHit) {
          found = true
          distance = iteration
          hits = hitCounter.count
        }

        iterationRDD = mapped.reduceByKey(reduce)
      }
    }

    println(s"Found: $found at distance = $distance, hits = $hits")
  }

  def parse(line: String, startHeroId: Int, maxDistance: Int): (Int, HeroNode) = {
    val fields = line.split(" ")

    val heroId = fields(0).toInt

    val (visitStatus, distance) =
      if (heroId == startHeroId) (VisitStatus.Visiting, 0)
      else (VisitStatus.NotVisited, maxDistance)

    (heroId, HeroNode(fields.drop(1).map(_.toInt), distance, visitStatus))
  }

  /**
    *
    * @param currentNode
    * @param hitCounter
    * @return
    */
  def expandNode(currentNode: (Int, HeroNode), hitCounter: LongAccumulator, targetHeroId: Int): Seq[(Int, HeroNode)] = {

    val (heroId, hero) = currentNode
    var expandedResults = Array(currentNode)

    if (hero.visitStatus == VisitStatus.Visiting) {
      expandedResults(0) = (heroId, HeroNode(hero.connections, hero.distance, VisitStatus.Visited))

      expandedResults ++= hero.connections.map(id => {
        if (targetHeroId == id) hitCounter add 1
        (id, HeroNode(Array(), hero.distance + 1, VisitStatus.Visiting))
      })
    }

    expandedResults
  }

  def reduce(a: HeroNode, b: HeroNode): HeroNode = {
    val newVisitStatus = (a.visitStatus, b.visitStatus) match {
      case (VisitStatus.Visited, _) | (_, VisitStatus.Visited)    => VisitStatus.Visited
      case (VisitStatus.Visiting, _) | (_, VisitStatus.Visiting)  => VisitStatus.Visiting
      case _                                                      => VisitStatus.NotVisited
    }

    val newDistance = if (a.distance < b.distance) a.distance else b.distance

    HeroNode(a.connections union b.connections distinct, newDistance, newVisitStatus)
  }

}
