name := "scala-slick-Test"

version := "0.1"

scalaVersion := "2.11.2"
libraryDependencies ++= Seq(
  "com.typesafe.slick" %% "slick" % "3.2.1",
  "mysql" % "mysql-connector-java" % "5.1.38",
  "com.typesafe.slick" %% "slick-hikaricp" % "3.2.1"
)
//logback+sfl4j
libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.2.3"
// https://mvnrepository.com/artifact/ch.qos.logback/logback-core
libraryDependencies += "ch.qos.logback" % "logback-core" % "1.2.3"
// https://mvnrepository.com/artifact/org.slf4j/slf4j-api
libraryDependencies += "org.slf4j" % "slf4j-api" % "1.7.25"

libraryDependencies += "com.typesafe.scala-logging" %% "scala-logging-slf4j" % "2.0.4"
