package com.david.phantomtest.infrastructure

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer

import scala.concurrent.ExecutionContext

object DefaultImplicits {
  implicit val system = ActorSystem("test-system")
  implicit val materializer = ActorMaterializer()
  implicit val executionContext = ExecutionContext.global
}
