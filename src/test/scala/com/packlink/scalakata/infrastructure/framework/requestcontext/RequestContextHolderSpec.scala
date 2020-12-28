package com.packlink.scalakata.infrastructure.framework.requestcontext

import java.util.concurrent.TimeUnit

import com.packlink.scalakata.BaseUnitSpec
import com.packlink.scalakata.infrastructure.fixtures.RequestContextFixtures
import org.scalatest.concurrent.ScalaFutures

import scala.concurrent.{ExecutionContext, Future}

class RequestContextHolderSpec extends BaseUnitSpec with ScalaFutures with RequestContextFixtures {

  implicit val ec: ExecutionContext = ExecutionContext.Implicits.global

  after {
    RequestContextHolder.removeRequestContext()
  }

  describe("RequestContextHolder") {

    it("should set properly the RequestContext") {
      // when
      RequestContextHolder.setRequestContext(aRequestContext)

      // then
      RequestContextHolder.getRequestContext should contain(aRequestContext)
    }

    it("should remove properly the RequestContext") {
      // given
      RequestContextHolder.setRequestContext(aRequestContext)

      // when
      RequestContextHolder.removeRequestContext()

      // then
      RequestContextHolder.getRequestContext shouldBe empty
    }

    it("should set properly the values from different threads") {
      // given
      val requestContext1 = aRequestContext
      val requestContext2 = aRequestContext.copy(correlationId = "patatas")

      // when
      val fRequestContext1 = Future {
        RequestContextHolder.setRequestContext(requestContext1)
        TimeUnit.MILLISECONDS.sleep(10)
        val maybeRequestContext1 = RequestContextHolder.getRequestContext
        RequestContextHolder.removeRequestContext()
        maybeRequestContext1
      }

      val fRequestContext2 = Future {
        RequestContextHolder.setRequestContext(requestContext2)
        TimeUnit.MILLISECONDS.sleep(10)
        val maybeRequestContext2 = RequestContextHolder.getRequestContext
        RequestContextHolder.removeRequestContext()
        maybeRequestContext2
      }

      // then
      whenReady {
        for {
          maybeRequestContext1 <- fRequestContext1
          maybeRequestContext2 <- fRequestContext2
        } yield (maybeRequestContext1, maybeRequestContext2)
      } { case (maybeRequestContext1, maybeRequestContext2) =>
        maybeRequestContext1 should contain(requestContext1)
        maybeRequestContext2 should contain(requestContext2)
      }
    }
  }
}
