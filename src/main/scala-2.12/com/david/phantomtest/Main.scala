package com.david.phantomtest

import java.util.UUID

import com.datastax.driver.core.utils.UUIDs
import com.david.phantomtest.infrastructure.MyDatabase
import com.david.phantomtest.user.User

import scala.concurrent.ExecutionContext.Implicits.global

object Main extends App {
  val store = MyDatabase.users.store(User(UUIDs.random(), "Mauricio"))
  store.onComplete( println )
}
