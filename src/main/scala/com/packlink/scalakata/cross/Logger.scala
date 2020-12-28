package com.packlink.scalakata.cross

trait Logger[F[_]] {

  def error(msg: String): F[Unit]

  def error(msg: String, err: Throwable): F[Unit]

  def warn(msg: String): F[Unit]

  def warn(msg: String, error: Throwable): F[Unit]

  def info(msg: String): F[Unit]

  def info(msg: String, error: Throwable): F[Unit]

  def debug(msg: String): F[Unit]

  def debug(msg: String, error: Throwable): F[Unit]
}

object Logger {

  def apply[F[_]](implicit ev: Logger[F]): Logger[F] = ev
}
