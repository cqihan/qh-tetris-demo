name := "tetris"

version := "1.0"

scalaVersion := "2.12.8"

addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.1" cross CrossVersion.full)

libraryDependencies ++= Seq(
    "org.scalafx" %% "scalafx" % "8.0.181-R13",
    "org.scalafx" %% "scalafxml-core-sfx8" % "0.4"
)

fork := true