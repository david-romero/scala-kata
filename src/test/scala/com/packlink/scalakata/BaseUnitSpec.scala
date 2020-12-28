package com.packlink.scalakata

import org.mockito.cats.IdiomaticMockitoCats
import org.mockito.scalatest.{IdiomaticMockito, ResetMocksAfterEachTest}
import org.scalatest.BeforeAndAfter
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

trait BaseUnitSpec
    extends AnyFunSpec
    with BeforeAndAfter
    with Matchers
    with IdiomaticMockito
    with ResetMocksAfterEachTest
    with IdiomaticMockitoCats
