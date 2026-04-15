val scala3Version = "3.8.2"

lazy val root = project
  .in(file("."))
  .settings(
    name := "graph-se",
    version := "0.1.0-SNAPSHOT",

    scalaVersion := scala3Version,

    libraryDependencies += "org.jgrapht" % "jgrapht-io" % "1.5.2",
    libraryDependencies += "com.github.pathikrit" %% "better-files" % "3.9.2",
    libraryDependencies += "org.scalameta" %% "munit" % "1.2.4" % Test
  )
