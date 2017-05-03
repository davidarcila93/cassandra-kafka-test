package com.david.phantomtest.common

import scala.concurrent.Future

trait Repository[A, IdType] {
  def query(id: IdType): Future[Option[A]]
  def store(a: A): Future[A]
}