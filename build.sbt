name := "AkkaFibonacciBenchmark"

version := "1.0"

scalaVersion := "2.9.1"

libraryDependencies ++= Seq(
  "se.scalablesolutions.akka" % "akka-actor" % "1.3-RC4",
  "se.scalablesolutions.akka" % "akka-typed-actor" % "1.3-RC4",
  "se.scalablesolutions.akka" % "akka-amqp" % "1.3-RC4",
  "se.scalablesolutions.akka" % "akka-testkit" % "1.3-RC4",
  "se.scalablesolutions.akka" % "akka-remote" % "1.3-RC4",
  "se.scalablesolutions.akka" % "akka-dispatcher-extras" % "1.3-RC4"
)
