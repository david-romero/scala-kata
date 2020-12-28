package com.packlink.scalakata.domain

import scalikejdbc._

case class Book(booksId: String, title : String, userId : String)

object Book extends SQLSyntaxSupport[Book] {
  override val tableName          = "books"
  def apply(rs: WrappedResultSet) = new Book(rs.string("book_id"),rs.string("title"), rs.string("user_id") )
}
