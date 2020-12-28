package com.packlink.scalakata.application.services

import com.packlink.scalakata.domain.{Book, BookCard, BookRepository, User, UserRepository}
import org.mockito.ArgumentMatchersSugar
import org.mockito.IdiomaticMockito.StubbingOps
import org.mockito.scalatest.MockitoSugar
import org.scalactic.Fail
import org.scalatest.BeforeAndAfter
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

import scala.collection.View.Empty
import scala.util.{Failure, Success}

class RetrieveBooksTest extends AnyFunSpec with BeforeAndAfter with Matchers with MockitoSugar with ArgumentMatchersSugar {

  val userRepository = mock[UserRepository]

  val bookRepository = mock[BookRepository]

  val retrieveBooks = new RetrieveBooks(userRepository, bookRepository)

  describe("Retrieve books") {
    it("should retrieve the books when the user exists ") {
      // given
      val mail = "davidromero@packlink.com"
      userRepository.findByEmail(mail) returns Success(Some(User("1", "David Romero", mail)))
      bookRepository.findByUser(User("1", "David Romero", mail)) returns Success(List(Book("1", "Scala from the beggining", "1")))

      // when
      val bookCard = retrieveBooks.execute(mail)

      // then
      bookCard shouldBe Success(BookCard(User("1", "David Romero", mail), List(Book("1", "Scala from the beggining", "1"))))
    }
    it("should throw an exception when the user is not found ") {
      // given
      val mail = "davidromero2@packlink.com"
      userRepository.findByEmail(mail) returns Success(None)

      // when
      val result = retrieveBooks.execute(mail)

      // then
      result shouldBe Failure(new IllegalArgumentException())
    }
  }

}
