name := "phantom-test"

version := "1.0"

scalaVersion := "2.12.2"

val phantomVersion = "2.7.4"

libraryDependencies ++= Seq(
  "org.scala-lang" % "scala-reflect" % scalaVersion.value,
  "com.outworkers"  %% "phantom-dsl" % phantomVersion,
  "com.typesafe.akka" %% "akka-stream-kafka" % "0.15"
)