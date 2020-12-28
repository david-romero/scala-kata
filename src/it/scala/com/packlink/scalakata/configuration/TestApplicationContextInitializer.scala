package com.packlink.scalakata.configuration

import org.springframework.context.{ApplicationContextInitializer, ConfigurableApplicationContext}

class TestApplicationContextInitializer extends ApplicationContextInitializer[ConfigurableApplicationContext] {

  override def initialize(applicationContext: ConfigurableApplicationContext): Unit =
    if (isLocalProfileActive(applicationContext)) {
      startLocalInfrastructure()
    }

  private def isLocalProfileActive(applicationContext: ConfigurableApplicationContext): Boolean = {
    val environment = applicationContext.getEnvironment
    val profiles    = environment.getActiveProfiles

    profiles.contains("it-local")
  }

  private def startLocalInfrastructure(): Unit =
    LocalInfrastructureContainer.start()
}
