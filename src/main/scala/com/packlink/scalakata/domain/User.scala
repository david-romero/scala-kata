package com.packlink.scalakata.domain

import scalikejdbc._

case class User(userId : String,name : String,mail : String)

object User extends SQLSyntaxSupport[User] {
  override val tableName          = "users"
  def apply(rs: WrappedResultSet) = new User( rs.string("user_id"),rs.string("name"), rs.string("mail"))
}
