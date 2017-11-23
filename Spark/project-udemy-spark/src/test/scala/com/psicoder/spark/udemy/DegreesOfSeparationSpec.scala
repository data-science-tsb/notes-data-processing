package com.psicoder.spark.udemy

import org.apache.spark.util.LongAccumulator

import com.psicoder.spark.udemy.DegreesOfSeparation.{HeroNode, VisitStatus}
import com.psicoder.spark.udemy.test.UnitSpec

class DegreesOfSeparationSpec extends UnitSpec {

  "DegreesOfSeparation.reduce(heroNodeA, heroNodeB)" should "choose Visited over NotVisited" in {
    val nodeA = HeroNode(Array(), 0, VisitStatus.Visited)
    val nodeB = HeroNode(Array(), 0, VisitStatus.NotVisited)

    val reducedNode = DegreesOfSeparation.reduce(nodeA, nodeB)

    assert(reducedNode.visitStatus === VisitStatus.Visited)
  }

  it should "choose Visited over NotVisited (Swapped Parameters)" in {
    val nodeA = HeroNode(Array(), 0, VisitStatus.NotVisited)
    val nodeB = HeroNode(Array(), 0, VisitStatus.Visited)

    val reducedNode = DegreesOfSeparation.reduce(nodeA, nodeB)

    assert(reducedNode.visitStatus === VisitStatus.Visited)
  }

  it should "choose Visited over Visiting" in {
    val nodeA = HeroNode(Array(), 0, VisitStatus.Visited)
    val nodeB = HeroNode(Array(), 0, VisitStatus.Visiting)

    val reducedNode = DegreesOfSeparation.reduce(nodeA, nodeB)

    assert(reducedNode.visitStatus === VisitStatus.Visited)
  }

  it should "choose Visited over Visiting (Swapped Parameters)" in {
    val nodeA = HeroNode(Array(), 0, VisitStatus.Visiting)
    val nodeB = HeroNode(Array(), 0, VisitStatus.Visited)

    val reducedNode = DegreesOfSeparation.reduce(nodeA, nodeB)

    assert(reducedNode.visitStatus === VisitStatus.Visited)
  }

  it should "choose Visiting over NotVisited" in {
    val nodeA = HeroNode(Array(), 0, VisitStatus.Visiting)
    val nodeB = HeroNode(Array(), 0, VisitStatus.NotVisited)

    val reducedNode = DegreesOfSeparation.reduce(nodeA, nodeB)

    assert(reducedNode.visitStatus === VisitStatus.Visiting)
  }

  it should "choose Visiting over NotVisited (Swapped Parameters)" in {
    val nodeA = HeroNode(Array(), 0, VisitStatus.NotVisited)
    val nodeB = HeroNode(Array(), 0, VisitStatus.Visiting)

    val reducedNode = DegreesOfSeparation.reduce(nodeA, nodeB)

    assert(reducedNode.visitStatus === VisitStatus.Visiting)
  }

  it should "choose preserve Visiting" in {
    val nodeA = HeroNode(Array(), 0, VisitStatus.Visiting)
    val nodeB = HeroNode(Array(), 0, VisitStatus.Visiting)

    val reducedNode = DegreesOfSeparation.reduce(nodeA, nodeB)

    assert(reducedNode.visitStatus === VisitStatus.Visiting)
  }

  it should "choose preserve Visited" in {
    val nodeA = HeroNode(Array(), 0, VisitStatus.Visited)
    val nodeB = HeroNode(Array(), 0, VisitStatus.Visited)

    val reducedNode = DegreesOfSeparation.reduce(nodeA, nodeB)

    assert(reducedNode.visitStatus === VisitStatus.Visited)
  }

  it should "choose preserve NotVisited" in {
    val nodeA = HeroNode(Array(), 0, VisitStatus.NotVisited)
    val nodeB = HeroNode(Array(), 0, VisitStatus.NotVisited)

    val reducedNode = DegreesOfSeparation.reduce(nodeA, nodeB)

    assert(reducedNode.visitStatus === VisitStatus.NotVisited)
  }

  it should "differentiate the status" in {
    assert(VisitStatus.Visited !== VisitStatus.Visiting)
    assert(VisitStatus.Visiting !== VisitStatus.NotVisited)
    assert(VisitStatus.NotVisited !== VisitStatus.Visited)
  }

  it should "pick the shorter distance" in {
    val nodeA = HeroNode(Array(), 1, VisitStatus.Visiting)
    val nodeB = HeroNode(Array(), 2, VisitStatus.Visiting)

    val reducedNode = DegreesOfSeparation.reduce(nodeA, nodeB)

    assert(reducedNode.distance === 1)
  }

  it should "combine the connections distinctly" in {
    val nodeA = HeroNode(Array(1, 2, 3, 4, 5), 1, VisitStatus.Visiting)
    val nodeB = HeroNode(Array(3, 4, 5, 6, 7, 8), 2, VisitStatus.Visiting)

    val reducedNode = DegreesOfSeparation.reduce(nodeA, nodeB)

    val connections = reducedNode.connections

    assert(connections.length === 8)

    assert(connections(0) === 1)
    assert(connections(4) === 5)
    assert(connections(5) === 6)
    assert(connections(7) === 8)
  }

  "DegreesOfSeparation.expandNode(HeroNode)" should """contain the current node with "Visited" """ in {

    val currentNode = (1, HeroNode(Array(2,3), 1, VisitStatus.Visiting))

    val expandedNodes: Seq[(Int, HeroNode)] = DegreesOfSeparation.expandNode(currentNode, new LongAccumulator(), 99)

    val actualNode = expandedNodes(0)
    assert(actualNode._1 === 1)
    assert(actualNode._2.visitStatus === VisitStatus.Visited)
  }

  it should """expand connections with "Visiting" status""" in {

    val currentNode = (1, HeroNode(Array(2), 1, VisitStatus.Visiting))

    val expandedNodes: Seq[(Int, HeroNode)] = DegreesOfSeparation.expandNode(currentNode, new LongAccumulator(), 99)

    val (id2, node2) = expandedNodes(1)

    assert(id2 === 2)
    assert(node2.visitStatus === VisitStatus.Visiting)
  }

  it should """not change the  "NotVisited" status when not visiting""" in {

    val currentNode = (1, HeroNode(Array(2), 1, VisitStatus.NotVisited))

    val expandedNodes: Seq[(Int, HeroNode)] = DegreesOfSeparation.expandNode(currentNode, new LongAccumulator(), 99)

    val (id, node) = expandedNodes(0)

    assert(id === 1)
    assert(node.visitStatus === VisitStatus.NotVisited)
  }

  it should "increment the counter when the target is hit" in {

    val targetHero = 100
    val currentNode = (1, HeroNode(Array(targetHero, 2, 3, 4), 1, VisitStatus.Visiting))

    val hitCounter = new LongAccumulator()

    DegreesOfSeparation.expandNode(currentNode, hitCounter, targetHero)

    assert(!hitCounter.isZero)
  }
}
