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
}
