name := "test"

version := "0.1"

scalaVersion := "2.12.4"

val refinedVersion                  = "0.8.7"
val akkaHttpVersion                 = "10.0.11"


lazy val refined     = "eu.timepit" %% "refined"      % refinedVersion
lazy val refinedCats = "eu.timepit" %% "refined-cats" % refinedVersion // optional
lazy val refinedEval = "eu.timepit" %% "refined-eval" % refinedVersion // optional, JVM-only
lazy val akkaHttpCore = "com.typesafe.akka" %% "akka-http-core" % akkaHttpVersion


libraryDependencies ++= Seq(akkaHttpCore, refined, refinedCats, refinedEval)