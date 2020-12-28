import sbt._

object Version {
  val springBoot     = "2.3.5.RELEASE"
  val springCloudGcp = "1.2.6.RELEASE"
  val micrometer     = "1.6.1"
  val janino         = "3.1.2"
  val jackson        = "2.11.3"
  val cats           = "2.2.0"
  val catsEffect     = "2.2.0"
  val scalatest      = "3.2.3"
  val mockitoScala   = "1.16.3"
  val testContainers = "1.15.0"
  val archUnit       = "0.14.1"
}

object Library {

  def springBootModule(artifact: String): ModuleID     = "org.springframework.boot"  % artifact % Version.springBoot
  def springCloudGcpModule(artifact: String): ModuleID = "org.springframework.cloud" % artifact % Version.springCloudGcp

  lazy val springBoot           = springBootModule("spring-boot-starter")
  lazy val springBootActuator   = springBootModule("spring-boot-starter-actuator")
  lazy val springBootValidation = springBootModule("spring-boot-starter-validation")
  lazy val springBootJson       = springBootModule("spring-boot-starter-json")

  lazy val springCloudGcpLogging = springCloudGcpModule("spring-cloud-gcp-starter-logging")
  lazy val springCloudGcpTrace   = springCloudGcpModule("spring-cloud-gcp-starter-trace")
  lazy val micrometer            = "io.micrometer" % "micrometer-registry-stackdriver" % Version.micrometer
  lazy val janino                = "org.codehaus.janino" % "janino" % Version.janino

  lazy val jacksonScalaModule = "com.fasterxml.jackson.module" %% "jackson-module-scala" % Version.jackson
  lazy val cats               = "org.typelevel"                %% "cats-core"            % Version.cats
  lazy val catsEffect         = "org.typelevel"                %% "cats-effect"          % Version.catsEffect
  lazy val springBootTest =
    springBootModule("spring-boot-starter-test") % "it, test" exclude ("org.mockito", "mockito-core")

  lazy val scalaTest        = "org.scalatest"       %% "scalatest"               % Version.scalatest      % "test, it"
  lazy val mockitoScala     = "org.mockito"         %% "mockito-scala"           % Version.mockitoScala   % "test, it"
  lazy val mockitoScalaTest = "org.mockito"         %% "mockito-scala-scalatest" % Version.mockitoScala   % "test, it"
  lazy val mockitoScalaCats = "org.mockito"         %% "mockito-scala-cats"      % Version.mockitoScala   % "test, it"
  lazy val testContainers   = "org.testcontainers"   % "testcontainers"          % Version.testContainers % "it"
  lazy val archUnit         = "com.tngtech.archunit" % "archunit"                % Version.archUnit       % "test"
  lazy val scalikejdbc      = "org.scalikejdbc"     %% "scalikejdbc"             % "3.5.+"
  lazy val h2               = "com.h2database"       % "h2"                      % "1.4.+"
  lazy val logback          = "ch.qos.logback"       % "logback-classic"         % "1.2.+"

}
