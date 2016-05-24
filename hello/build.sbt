



libraryDependencies += "org.scalactic" %% "scalactic" % "2.2.6"
lazy val root = (project in file(".")).
  settings(
    name := "hello",
    version := "0.0.1",
    scalaVersion := "2.11.7"
  )

libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.6" % "test"
libraryDependencies += "org.scalafx" %% "scalafx" % "8.0.92-R10"
