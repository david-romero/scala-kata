package com.packlink.scalakata.infrastructure.persistence

import com.packlink.scalakata.application.services.RetrieveBooks
import com.packlink.scalakata.domain.BookCard
import org.springframework.boot.CommandLineRunner
import scalikejdbc.{AutoSession, ConnectionPool, ConnectionPoolSettings, scalikejdbcSQLInterpolationImplicitDef}

object BootstrapData extends App {

  // initialize JDBC driver & connection pool
  Class.forName("org.h2.Driver")
  val settings = ConnectionPoolSettings(initialSize = 5,
    maxSize = 20,
    connectionTimeoutMillis = 3000L,
    validationQuery = "select 1 from dual")
  ConnectionPool.singleton("jdbc:h2:mem:library", "user", "pass", settings)
  // ad-hoc session provider on the REPL
  implicit val session = AutoSession
  // table creation, you can run DDL by using #execute as same as JDBC
  sql"""
create table users (
  user_id serial not null primary key,
  mail varchar(64),
  name varchar(64)
)
""".execute().apply()
  sql"""
create table books (
  book_id serial not null primary key,
  title varchar(64),
  user_id varchar(64)
)
""".execute().apply()
  // insert initial data
  Seq(("alice@gmail.com", "Alice"), ("bob@gmail.com", "Bob"), ("chris@gmail.com", "Chris")) foreach { tuple =>
    sql"insert into users (mail, name) values (${tuple._1}, ${tuple._2})".update().apply()
  }
  Seq(("Alice's adventures in Wonderland", "1"), ("Tenet", "2")) foreach { tuple =>
    sql"insert into books (title, user_id) values (${tuple._1}, ${tuple._2})".update().apply()
  }
  val libraryService = new RetrieveBooks(new H2UserRepository(), new H2BookRepository())
  val result = libraryService.execute("bob@gmail.com")
  println(s"This is the found reservation ${result}")
}
