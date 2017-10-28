name := "project-quickstart"

version := "1.0"

scalaVersion := "2.11.4"

val sparkVer = "2.1.0"

lazy val providedDependencies = Seq(
  "org.apache.spark" %% "spark-core" % sparkVer,
  "org.apache.spark" %% "spark-sql" % sparkVer,
)

//set to false if compiling for deployment

val runIDE = true

if (runIDE) {
  libraryDependencies ++= providedDependencies.map(_ % "compile" withSources())
} else {
  libraryDependencies ++= providedDependencies.map(_ % "provided" withSources())
}

libraryDependencies ++= Seq(
  "org.mongodb.spark" %% "mongo-spark-connector" % "2.0.0",
  "org.postgresql:postgresql:42.1.1"
)