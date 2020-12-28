lazy val commonScalacOptions = Seq(
  "-feature",
  "-deprecation",
  "-unchecked",
  "-Wunused:imports,patvars,locals",
  "-Wnumeric-widen",
  "-Xlint:-unused"
)

lazy val root =
  project
    .in(file("."))
    .settings(
      name := "scalakata",
      organization := "com.packlink",
      version := "1.0.0",
      scalaVersion := "2.13.4",
      libraryDependencies ++= appDependencies,
      libraryDependencies ++= testDependencies,
      scalacOptions ++= commonScalacOptions
    )
    .enablePlugins(JavaAppPackaging)
    .configs(IntegrationTest)
    .settings(
      inConfig(IntegrationTest)(Defaults.itSettings),
      inConfig(IntegrationTest)(org.scalafmt.sbt.ScalafmtPlugin.scalafmtConfigSettings),
      IntegrationTest / fork := true,
      IntegrationTest / testForkedParallel := true
    )

lazy val appDependencies = Seq(
  Library.springBoot,
  Library.springBootActuator,
  Library.springBootValidation,
  Library.springBootJson,
  Library.janino,
  Library.jacksonScalaModule,
  Library.cats,
  Library.catsEffect,
  Library.scalikejdbc,
  Library.h2,
  Library.logback
)

lazy val testDependencies = Seq(
  Library.springBootTest,
  Library.mockitoScala,
  Library.mockitoScalaTest,
  Library.mockitoScalaCats,
  Library.scalaTest,
  Library.archUnit,
  Library.testContainers,
)
