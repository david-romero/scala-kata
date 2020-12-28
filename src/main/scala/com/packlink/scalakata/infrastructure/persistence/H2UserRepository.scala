package com.packlink.scalakata.infrastructure.persistence

import com.packlink.scalakata.domain.{User, UserRepository}
import scalikejdbc._

import scala.util.Try

class H2UserRepository(implicit session: DBSession) extends UserRepository {

  val u = User.syntax("u")

  override def findByEmail(email: String): Try[Option[User]] =
    Try(
      withSQL {
        select.from(User as u).where.eq(u.mail, email)
      }.map(rs => User(rs)).single().apply()
    )
}
