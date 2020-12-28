package com.packlink.scalakata.infrastructure.framework.configuration

import java.time.Clock

import org.springframework.context.annotation.{Bean, Configuration}

@Configuration
class ClockConfiguration {
  @Bean
  def clock: Clock = Clock.systemDefaultZone()
}
