package com.packlink.scalakata

import com.packlink.scalakata.configuration.{TestActiveProfilesResolver, TestApplicationContextInitializer}
import org.mockito.MockitoSugar
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers
import org.scalatest.{BeforeAndAfter, BeforeAndAfterAll}
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.test.context.{ActiveProfiles, ContextConfiguration, TestContextManager}

@ContextConfiguration(initializers = Array(classOf[TestApplicationContextInitializer]))
@ActiveProfiles(resolver = classOf[TestActiveProfilesResolver])
@SpringBootTest(
  classes = Array(classOf[ScalakataApplication]),
  webEnvironment = WebEnvironment.RANDOM_PORT
)
abstract class BaseIntegrationSpec
    extends AnyFunSpec
    with BeforeAndAfter
    with BeforeAndAfterAll
    with MockitoSugar
    with Matchers {

  protected val testContextManager: TestContextManager = new TestContextManager(this.getClass)

  override def beforeAll(): Unit = testContextManager.prepareTestInstance(this)
}
