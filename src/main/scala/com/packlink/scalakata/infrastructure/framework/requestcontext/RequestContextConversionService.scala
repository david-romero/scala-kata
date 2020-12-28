package com.packlink.scalakata.infrastructure.framework.requestcontext

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.core.convert.{ConversionService, TypeDescriptor}
import org.springframework.stereotype.Component

@Component
class RequestContextConversionService(objectMapper: ObjectMapper) extends ConversionService {

  override def canConvert(sourceType: Class[_], targetType: Class[_]): Boolean =
    classOf[String].isAssignableFrom(sourceType) && classOf[RequestContext].isAssignableFrom(targetType)

  override def canConvert(sourceType: TypeDescriptor, targetType: TypeDescriptor): Boolean =
    classOf[String].isAssignableFrom(sourceType.getObjectType) && classOf[RequestContext].isAssignableFrom(
      targetType.getObjectType
    )

  override def convert[T](source: Any, targetType: Class[T]): T =
    objectMapper.readValue(source.asInstanceOf[String], targetType)

  override def convert(source: Any, sourceType: TypeDescriptor, targetType: TypeDescriptor): AnyRef =
    objectMapper.readValue(source.asInstanceOf[String], targetType.getObjectType)
}
