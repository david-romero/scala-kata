package com.packlink.scalakata.infrastructure.framework.requestcontext

import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.Logger

import scala.util.{Failure, Try}

case class RequestContext(
  correlationId: String,
  platform: String,
  platformCountry: String,
  clientId: Option[String],
  userId: Option[String]
)

object RequestContext {

  final val HeaderName = "X-Request-Context"

  def parse(headerValue: String, mapper: ObjectMapper, logger: Logger): Option[RequestContext] =
    Try {
      mapper.readValue(headerValue, classOf[RequestContext])
    }.recoverWith { case error =>
      logger.error(s"Error parsing $HeaderName header value $headerValue into a request context", error)
      Failure(error)
    }.toOption

  implicit class RequestContextJsonWriterOps(requestContext: RequestContext) {
    def writeAsJson(objectMapper: ObjectMapper): String =
      objectMapper.writeValueAsString(requestContext)
  }
}
