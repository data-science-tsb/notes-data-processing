package com.psicoder.spark.udemy

import com.psicoder.spark.udemy.DegreesOfSeparation.{HeroNode, VisitStatus}
import com.psicoder.spark.udemy.test.UnitSpec

class DegreesOfSeparationSpec extends UnitSpec {

  "Reduce" should "choose the Visited Status when one of the parameter is visited" in {
    val nodeA = HeroNode(Array(), 0, VisitStatus.Visited)
    val nodeB = HeroNode(Array(), 0, VisitStatus.NotVisited)

    val reducedNode = DegreesOfSeparation.reduce(nodeA, nodeB)

    assert(reducedNode.visitStatus == VisitStatus.Visited)
  }

  "Reduce" should "choose the Visiting Status when one of the parameter is visiting" in {
    val nodeA = HeroNode(Array(), 0, VisitStatus.Visiting)
    val nodeB = HeroNode(Array(), 0, VisitStatus.NotVisited)

    val reducedNode = DegreesOfSeparation.reduce(nodeA, nodeB)

    assert(reducedNode.visitStatus == VisitStatus.Visiting)
  }

  "Reduce" should "pick the shorter distance" in {
    val nodeA = HeroNode(Array(), 1, VisitStatus.Visiting)
    val nodeB = HeroNode(Array(), 2, VisitStatus.Visiting)

    val reducedNode = DegreesOfSeparation.reduce(nodeA, nodeB)

    assert(reducedNode.distance == 1)
  }

  "Reduce" should "combine the connections distinctly" in {
    val nodeA = HeroNode(Array(1, 2, 3, 4, 5), 1, VisitStatus.Visiting)
    val nodeB = HeroNode(Array(3, 4, 5, 6, 7, 8), 2, VisitStatus.Visiting)

    val reducedNode = DegreesOfSeparation.reduce(nodeA, nodeB)

    val connections = reducedNode.connections

    assert(connections.length == 8)

    assert(connections(0) == 1)
    assert(connections(4) == 5)
    assert(connections(5) == 6)
    assert(connections(7) == 8)
  }
}
