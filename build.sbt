
version := "0.1"
scalaVersion := "2.12.4"

val refinedVersion                  = "0.8.7"
val akkaHttpVersion                 = "10.1.0"


lazy val refined     = "eu.timepit" %% "refined"      % refinedVersion
lazy val refinedCats = "eu.timepit" %% "refined-cats" % refinedVersion // optional
lazy val refinedEval = "eu.timepit" %% "refined-eval" % refinedVersion // optional, JVM-only
lazy val akkaHttpCore = "com.typesafe.akka" %% "akka-http-core" % akkaHttpVersion


lazy val refinements = project
  .in(file("modules/refinements"))
  .settings(
    name       := "test-refinements",
    moduleName := "test-refinements",
    libraryDependencies ++= Seq(akkaHttpCore, refined, refinedCats, refinedEval)
  )

lazy val core = project
  .in(file("modules/core"))
    .dependsOn(refinements)
  .settings(
    name       := "test-core",
    moduleName := "test-core"
  )

lazy val root = project
  .in(file("."))
  .settings(
    name                  := "test",
    moduleName            := "test"
  )
  .aggregate(refinements, core)
