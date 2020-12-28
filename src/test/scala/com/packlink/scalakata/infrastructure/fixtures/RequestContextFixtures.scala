package com.packlink.scalakata.infrastructure.fixtures

import com.packlink.scalakata.infrastructure.framework.requestcontext.RequestContext

trait RequestContextFixtures {

  lazy val aRequestContext: RequestContext =
    RequestContext(
      correlationId = "6eee315e-0e56-46d4-8e21-0775f1a2118b",
      platform = "PRO",
      platformCountry = "ES",
      clientId = Option("7b99a5ea-1a17-4e93-8a8c-44290baddb75"),
      userId = Option("509ae8f5-e9ae-4890-a5b8-30435c633550")
    )

  lazy val aRequestContextHeaderValue: String =
    """{
      |"correlation_id": "6eee315e-0e56-46d4-8e21-0775f1a2118b",
      |"platform": "PRO",
      |"platform_country": "ES",
      |"client_id": "7b99a5ea-1a17-4e93-8a8c-44290baddb75"
      |"user_id": "509ae8f5-e9ae-4890-a5b8-30435c633550"
      |}""".stripMargin

}
