package com.packlink.scalakata.application.services

import com.packlink.scalakata.domain.{BookCard, BookRepository, User, UserRepository}

import scala.util.{Failure, Success, Try}

class RetrieveBooks(userRepository: UserRepository, bookRepository: BookRepository) {

  def execute(mail: String): Try[BookCard] = {
    for {
      maybeUser <- userRepository.findByEmail(mail)
      user <- maybeUser.fold[Try[User]](Failure(new IllegalArgumentException()))(Success.apply)
      books <- bookRepository.findByUser(user)
    } yield BookCard(user, books)
  }
}
