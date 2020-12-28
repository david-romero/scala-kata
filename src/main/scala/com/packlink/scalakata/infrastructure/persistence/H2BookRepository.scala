package com.packlink.scalakata.infrastructure.persistence

import com.packlink.scalakata.domain.{Book, BookRepository, User}
import scalikejdbc._

import scala.util.Try

class H2BookRepository(implicit session: DBSession) extends BookRepository {

  val b = Book.syntax("b")

  override def findByUser(user: User): Try[List[Book]] =
    Try(
      withSQL {
        select.from(Book as b).where.eq(b.userId, user.userId)
      }.map(rs => Book(rs)).list().apply()
    )
}
