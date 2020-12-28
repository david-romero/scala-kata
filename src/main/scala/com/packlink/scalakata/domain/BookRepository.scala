package com.packlink.scalakata.domain

import scala.util.Try

trait BookRepository {

  def findByUser(user : User) : Try[List[Book]]

}
