package com.packlink.scalakata

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class ScalakataApplication

object ScalakataApplication extends App {
  SpringApplication.run(classOf[ScalakataApplication], args: _*)
}
