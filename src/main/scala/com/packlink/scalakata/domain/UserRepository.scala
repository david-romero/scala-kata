package com.packlink.scalakata.domain

import scala.util.Try

trait UserRepository {

  def findByEmail(mail: String): Try[Option[User]]

}
