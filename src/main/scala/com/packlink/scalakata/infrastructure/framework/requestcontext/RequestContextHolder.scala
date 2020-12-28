package com.packlink.scalakata.infrastructure.framework.requestcontext

import org.springframework.core.NamedThreadLocal

object RequestContextHolder {

  private val requestContextThreadLocal: ThreadLocal[RequestContext] =
    new NamedThreadLocal[RequestContext]("request-context")

  def getRequestContext: Option[RequestContext] =
    Option(requestContextThreadLocal.get())

  def setRequestContext(requestContext: RequestContext): Unit =
    requestContextThreadLocal.set(requestContext)

  def removeRequestContext(): Unit =
    requestContextThreadLocal.remove()
}
