# Degrees of Separation: Breadth-First Search

## Scala
```scala
// The characters we wish to find the degree of separation between:
val startCharacterID: Long = 5306 //SpiderMan
val targetCharacterID: Long = 14  //ADAM 3,031 (who?)

val DEFAULT_DISTANCE = Int.MaxValue

//scala does not have enums? well thats not appealing at all :(
val DEFAULT = 0 //initial state
val VISITED = 1 //visited
val EXPANDED = 2 //expanded

/**
 * connections - Ids of the Marvel hero's cooccurences
 * distance - current distance between this hero and the target hero, start with the max distance
 * expandedState - (0, 1, 2)
 */
case class Hero(connections: List[Long], distance: Int, expandedState: Int) {
    
    def fold(otherHero: Hero): Hero = {
        val newDistance = Math.min(distance, otherHero.distance)
        val newState = Math.max(expandedState, otherHero.expandedState)
        val newConnections = (connections ++ otherHero.connections).distinct
        
        Hero(newConnections, newDistance, newState)
    }
    
    override def toString() = s"Distance:$distance State:$expandedState Connections:$connections"
}

// Our accumulator, used to signal when we find the target character during
// our BFS traversal.
val hitCounter = sc.accumulator(0)

// initial state of the RDD, only the start character
// is visited
def convertToBFS(line: String): (Long, Hero) = {
    val heroID::connections = line.split(" ").map(_.toLong).toList
    
    if (heroID == startCharacterID) {
        return (heroID, Hero(connections, 0, VISITED))
    }

    (heroID, Hero(connections, DEFAULT_DISTANCE, DEFAULT))
}

def createStartingRdd() = sc.textFile("Marvel-Graph.txt").map(convertToBFS)

def bfsFlatMap(node: (Long, Hero)): List[(Long, Hero)] = {
    val currentHero = node._2
    var results = List(node)
    
    if (currentHero.expandedState == VISITED) {
        results = results ++ currentHero.connections.map(connection => {
            if (connection == targetCharacterID) {
                hitCounter += 1
            }
            (connection, Hero(List(), currentHero.distance+1, VISITED))
        })
        results :+ (node._1, Hero(currentHero.connections, currentHero.distance, EXPANDED))
    }
    
    results
}

var iterationRDD = createStartingRdd()

import scala.util.control.Breaks._

breakable {
for (iteration <- 0 to 5) {
    println(s"Running BFS iteration #${iteration+1}")
    
    // Create new vertices as needed to darken or reduce distances in the
    // reduce stage. If we encounter the node we're looking for as a GRAY
    // node, increment our accumulator to signal that we're done.
    val mapped = iterationRDD.flatMap(bfsFlatMap)
    
    // Note that mapped.count() action here forces the RDD to be evaluated, and
    // that's the only reason our accumulator is actually updated.
    println(s"Processing ${mapped.count()} values.")
    
    if (hitCounter.value > 0) {
        println(s"Hit the target character! From ${hitCounter.value} different direction(s).")
        break
    }
    
    // Reducer combines data for each character ID, preserving the darkest
    // color and shortest path.
    iterationRDD = mapped.reduceByKey((x,y) => x fold y)
}
}
```

## Python
```python
# The characters we wish to find the degree of separation between:
startCharacterID = 5306 #SpiderMan
targetCharacterID = 14  #ADAM 3,031 (who?)

# Our accumulator, used to signal when we find the target character during
# our BFS traversal.
hitCounter = sc.accumulator(0)

def convertToBFS(line):
    fields = line.split()
    heroID = int(fields[0])
    connections = []
    for connection in fields[1:]:
        connections.append(int(connection))

    color = 'WHITE'
    distance = 9999

    if (heroID == startCharacterID):
        color = 'GRAY'
        distance = 0

    return (heroID, (connections, distance, color))


def createStartingRdd():
    inputFile = sc.textFile("Marvel-Graph.txt")
    return inputFile.map(convertToBFS)

def bfsMap(node):
    characterID = node[0]
    data = node[1]
    connections = data[0]
    distance = data[1]
    color = data[2]

    results = []

    #If this node needs to be expanded...
    if (color == 'GRAY'):
        for connection in connections:
            newCharacterID = connection
            newDistance = distance + 1
            newColor = 'GRAY'
            if (targetCharacterID == connection):
                hitCounter.add(1)

            newEntry = (newCharacterID, ([], newDistance, newColor))
            results.append(newEntry)

        #We've processed this node, so color it black
        color = 'BLACK'

    #Emit the input node so we don't lose it.
    results.append( (characterID, (connections, distance, color)) )
    return results

def bfsReduce(data1, data2):
    edges1 = data1[0]
    edges2 = data2[0]
    distance1 = data1[1]
    distance2 = data2[1]
    color1 = data1[2]
    color2 = data2[2]

    distance = 9999
    color = color1
    edges = []

    # See if one is the original node with its connections.
    # If so preserve them.
    if (len(edges1) > 0):
        edges.extend(edges1)
    if (len(edges2) > 0):
        edges.extend(edges2)

    # Preserve minimum distance
    if (distance1 < distance):
        distance = distance1

    if (distance2 < distance):
        distance = distance2

    # Preserve darkest color
    if (color1 == 'WHITE' and (color2 == 'GRAY' or color2 == 'BLACK')):
        color = color2

    if (color1 == 'GRAY' and color2 == 'BLACK'):
        color = color2

    if (color2 == 'WHITE' and (color1 == 'GRAY' or color1 == 'BLACK')):
        color = color1

    if (color2 == 'GRAY' and color1 == 'BLACK'):
        color = color1

    return (edges, distance, color)


#Main program here:
iterationRdd = createStartingRdd()

for iteration in range(0, 10):
    print("Running BFS iteration# " + str(iteration+1))

    # Create new vertices as needed to darken or reduce distances in the
    # reduce stage. If we encounter the node we're looking for as a GRAY
    # node, increment our accumulator to signal that we're done.
    mapped = iterationRdd.flatMap(bfsMap)

    # Note that mapped.count() action here forces the RDD to be evaluated, and
    # that's the only reason our accumulator is actually updated.
    print("Processing " + str(mapped.count()) + " values.")

    if (hitCounter.value > 0):
        print("Hit the target character! From " + str(hitCounter.value) \
            + " different direction(s).")
        break

    # Reducer combines data for each character ID, preserving the darkest
    # color and shortest path.
    iterationRdd = mapped.reduceByKey(bfsReduce)

```
