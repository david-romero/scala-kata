package com.packlink.scalakata.infrastructure.framework.requestcontext

import com.fasterxml.jackson.databind.ObjectMapper
import com.packlink.scalakata.BaseUnitSpec
import com.packlink.scalakata.infrastructure.fixtures.RequestContextFixtures
import org.springframework.core.ResolvableType
import org.springframework.core.convert.TypeDescriptor

import scala.reflect.ClassTag

class RequestContextConversionServiceSpec extends BaseUnitSpec with RequestContextFixtures {

  describe("on canConvert") {
    it("should return true when the source is a String and the target is a RequestContext") {
      // given
      val objectMapper = mock[ObjectMapper]
      val subject      = new RequestContextConversionService(objectMapper)

      // when
      val actual = subject.canConvert(classOf[String], classOf[RequestContext])

      // then
      actual shouldBe true
    }

    it("should return false when the source is not a String") {
      // given
      val objectMapper = mock[ObjectMapper]
      val subject      = new RequestContextConversionService(objectMapper)

      // when
      val actual = subject.canConvert(classOf[Int], classOf[RequestContext])

      // then
      actual shouldBe false
    }

    it("should return false when the target is not a RequestContext") {
      // given
      val objectMapper = mock[ObjectMapper]
      val subject      = new RequestContextConversionService(objectMapper)

      // when
      val actual = subject.canConvert(classOf[String], classOf[Int])

      // then
      actual shouldBe false
    }
  }

  describe("on canConvert (with TypeDescriptor)") {
    it("should return true when the source is a String and the target is a RequestContext") {
      // given
      val objectMapper = mock[ObjectMapper]
      val subject      = new RequestContextConversionService(objectMapper)

      // when
      val actual = subject.canConvert(typeDescriptorForClass[String], typeDescriptorForClass[RequestContext])

      // then
      actual shouldBe true
    }

    it("should return false when the source is not a String") {
      // given
      val objectMapper = mock[ObjectMapper]
      val subject      = new RequestContextConversionService(objectMapper)

      // when
      val actual = subject.canConvert(typeDescriptorForClass[Int], typeDescriptorForClass[RequestContext])

      // then
      actual shouldBe false
    }

    it("should return false when the target is not a RequestContext") {
      // given
      val objectMapper = mock[ObjectMapper]
      val subject      = new RequestContextConversionService(objectMapper)

      // when
      val actual = subject.canConvert(typeDescriptorForClass[String], typeDescriptorForClass[Int])

      // then
      actual shouldBe false
    }
  }

  describe("on convert") {
    it("should read the object from the json representation") {
      // given
      val objectMapper = mock[ObjectMapper]
      val subject      = new RequestContextConversionService(objectMapper)

      objectMapper.readValue(aRequestContextHeaderValue, classOf[RequestContext]) returns aRequestContext

      // when
      val actual = subject.convert(aRequestContextHeaderValue, classOf[RequestContext])

      // then
      actual shouldBe aRequestContext
    }
  }

  describe("on convert (with TypeDescriptor)") {
    it("should read the object from the json representation") {
      // given
      val objectMapper = mock[ObjectMapper]
      val subject      = new RequestContextConversionService(objectMapper)

      objectMapper.readValue(aRequestContextHeaderValue, classOf[RequestContext]) returns aRequestContext

      // when
      val actual = subject.convert(
        aRequestContextHeaderValue,
        typeDescriptorForClass[String],
        typeDescriptorForClass[RequestContext]
      )

      // then
      actual shouldBe aRequestContext
    }
  }

  private def typeDescriptorForClass[T: ClassTag] =
    new TypeDescriptor(
      ResolvableType.forClass(implicitly[ClassTag[T]].runtimeClass),
      implicitly[ClassTag[T]].runtimeClass,
      Array()
    )
}
