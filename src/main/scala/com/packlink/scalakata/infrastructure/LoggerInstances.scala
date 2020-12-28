package com.packlink.scalakata.infrastructure

import cats.effect.Sync
import com.packlink.scalakata.cross.Logger
import org.slf4j
import org.slf4j.LoggerFactory

object LoggerInstances {

  def sync[F[_]: Sync](clazz: Class[_]): Logger[F] = new Logger[F] {
    val logger: slf4j.Logger = LoggerFactory.getLogger(clazz)

    override def error(msg: String): F[Unit] = Sync[F].delay(logger.error(msg))

    override def error(msg: String, e: Throwable): F[Unit] = Sync[F].delay(logger.error(msg, e))

    override def warn(msg: String): F[Unit] = Sync[F].delay(logger.warn(msg))

    override def warn(msg: String, error: Throwable): F[Unit] = Sync[F].delay(logger.warn(msg, error))

    override def info(msg: String): F[Unit] = Sync[F].delay(logger.info(msg))

    override def info(msg: String, error: Throwable): F[Unit] = Sync[F].delay(logger.info(msg, error))

    override def debug(msg: String): F[Unit] = Sync[F].delay(logger.debug(msg))

    override def debug(msg: String, error: Throwable): F[Unit] = Sync[F].delay(logger.debug(msg, error))
  }
}
