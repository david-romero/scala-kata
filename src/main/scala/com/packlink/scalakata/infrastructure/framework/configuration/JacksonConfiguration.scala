package com.packlink.scalakata.infrastructure.framework.configuration

import com.fasterxml.jackson.databind.Module
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import org.springframework.context.annotation.{Bean, Configuration}

@Configuration
class JacksonConfiguration {

  @Bean
  def jacksonScalaModule: Module = DefaultScalaModule

}
