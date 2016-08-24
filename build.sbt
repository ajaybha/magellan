name := "magellan"

version := "1.0.4-SNAPSHOT"

organization := "yuanzhaoYZ"

scalaVersion := "2.11.7"

crossScalaVersions := Seq("2.11.7")

sparkVersion := "1.6.1"

val testSparkVersion = settingKey[String]("The version of Spark to test against.")

testSparkVersion := sys.props.get("spark.testVersion").getOrElse(sparkVersion.value)

val testHadoopVersion = settingKey[String]("The version of Hadoop to test against.")

testHadoopVersion := sys.props.getOrElse("hadoop.testVersion", "2.2.0")

sparkComponents := Seq("core", "sql")

libraryDependencies ++= Seq(
  "commons-io" % "commons-io" % "2.4",
  "org.slf4j" % "slf4j-api" % "1.7.5" % "provided",
  "org.scalatest" %% "scalatest" % "2.2.1" % "test"
)

libraryDependencies ++= Seq(
  "org.apache.hadoop" % "hadoop-client" % testHadoopVersion.value % "test",
  "org.apache.spark" %% "spark-core" % testSparkVersion.value % "test" exclude("org.apache.hadoop", "hadoop-client"),
  "org.apache.spark" %% "spark-sql" % testSparkVersion.value % "test" exclude("org.apache.hadoop", "hadoop-client")
)

// This is necessary because of how we explicitly specify Spark dependencies
// for tests rather than using the sbt-spark-package plugin to provide them.
spIgnoreProvided := true

publishMavenStyle := true

spAppendScalaVersion := true

spIncludeMaven := true

publishTo := {
  val nexus = "https://oss.sonatype.org/"
  if (version.value.endsWith("SNAPSHOT"))
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases"  at nexus + "service/local/staging/deploy/maven2")
}

pomExtra := (
  <url>https://github.com/yuanzhaoYZ/magellan</url>
  <licenses>
    <license>
      <name>Apache License, Verision 2.0</name>
      <url>http://www.apache.org/licenses/LICENSE-2.0.html</url>
      <distribution>repo</distribution>
    </license>
  </licenses>
  <scm>
    <url>git@github.com:yuanzhaoYZ/magellan.git</url>
    <connection>scm:git:git@github.com:yuanzhaoYZ/magellan.git</connection>
  </scm>
  <developers>
    <developer>
      <id>yuanzhaoYZ</id>
      <name>Yuan Zhao</name>
      <url>www.linkedin.com/in/yuanzhao0501</url>
    </developer>
  </developers>)

spName := "yuanzhaoYZ/magellan"
spShortDescription := "Geo Spatial Data Analytics on Spark"
spDescription := "Magellan /scala 2.11/spark 1.6.1"
parallelExecution in Test := false

ScoverageSbtPlugin.ScoverageKeys.coverageHighlighting := {
  if (scalaBinaryVersion.value == "2.11") false
  else true
}

credentials += Credentials(Path.userHome / ".ivy2" / ".sbtcredentials")

licenses += "Apache-2.0" -> url("http://opensource.org/licenses/Apache-2.0")
