
import com.typesafe.sbt.SbtScalariform._
import sbt.Keys._
import sbt._
import com.typesafe.sbt.SbtNativePackager._

//SbtNativePackager.packageArchetype.java_application

//enablePlugins(JavaAppPackaging)

//com.typesafe.sbt.packageArchetype.java_application

object CvBuild extends Build {

  lazy val cv = Project("vote", file("."))
    .settings(projectBuildSettings: _*)
    .settings(packageArchetype.java_application: _*)
    .settings(libraryDependencies ++= Seq(
    "org.seleniumhq.selenium" % "selenium-java" % "2.45.0"
  ))


  val VERSION = "1.0.0-SNAPSHOT"

  val basicSettings = Defaults.coreDefaultSettings ++ Seq(
    version := VERSION,
    homepage := Some(new URL("https://demo.com/developer/demo-customer-assistant")),
    organization := "com.demo",
    organizationHomepage := Some(new URL("https://demo.com")),
    description := "demo customer assistant.",
    startYear := Some(2015),
    scalaVersion := "2.11.4",
    scalacOptions := Seq(
      "-encoding", "utf8",
      "-feature",
      "-unchecked",
      "-deprecation",
      "-target:jvm-1.6",
      "-language:postfixOps",
      "-language:implicitConversions",
      "-Xlog-reflective-calls"
    )
  )

  import net.virtualvoid.sbt.graph.Plugin._

  lazy val projectBuildSettings = basicSettings ++ formattingSettings ++ graphSettings ++ Seq(publishMavenStyle := true)

  import scalariform.formatter.preferences._
  val formattingSettings = scalariformSettings ++ Seq(
    ScalariformKeys.preferences := ScalariformKeys.preferences.value
      .setPreference(RewriteArrowSymbols, true)
      .setPreference(AlignParameters, true)
      .setPreference(AlignSingleLineCaseStatements, true)
      .setPreference(DoubleIndentClassDeclaration, true)
      .setPreference(PreserveDanglingCloseParenthesis, true))


}

