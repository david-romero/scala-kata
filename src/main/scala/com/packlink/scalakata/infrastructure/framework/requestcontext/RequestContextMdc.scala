package com.packlink.scalakata.infrastructure.framework.requestcontext

import org.slf4j.MDC

object RequestContextMdc {

  def put(requestContext: RequestContext): Unit = {
    MDC.put(RequestContextMdcKeyNames.CorrelationId, requestContext.correlationId)
    MDC.put(RequestContextMdcKeyNames.Platform, requestContext.platform)
    MDC.put(RequestContextMdcKeyNames.PlatformCountry, requestContext.platformCountry)
    MDC.put(RequestContextMdcKeyNames.ClientId, requestContext.clientId.orNull)
    MDC.put(RequestContextMdcKeyNames.UserId, requestContext.userId.orNull)
  }

  def remove(): Unit = {
    MDC.remove(RequestContextMdcKeyNames.CorrelationId)
    MDC.remove(RequestContextMdcKeyNames.Platform)
    MDC.remove(RequestContextMdcKeyNames.PlatformCountry)
    MDC.remove(RequestContextMdcKeyNames.ClientId)
    MDC.remove(RequestContextMdcKeyNames.UserId)
  }
}

object RequestContextMdcKeyNames {
  final val CorrelationId: String   = "correlationId"
  final val Platform: String        = "platform"
  final val PlatformCountry: String = "platformCountry"
  final val ClientId: String        = "clientId"
  final val UserId: String          = "userId"
}
