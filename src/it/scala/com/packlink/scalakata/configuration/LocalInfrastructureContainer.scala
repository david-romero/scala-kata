package com.packlink.scalakata.configuration

import java.io.File
import java.time.Duration

import org.testcontainers.containers.DockerComposeContainer
import org.testcontainers.containers.wait.strategy.{Wait, WaitStrategy}

object LocalInfrastructureContainer {
  private val configuration = new File("src/it/resources/infrastructure-it-local.yml")
  lazy val container: DockerComposeContainer[Nothing] = new DockerComposeContainer(configuration)

  def start(): Unit = container.start()

  object WaitingStrategies {
  }
}
