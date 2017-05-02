package com.david.phantomtest

import akka.kafka.scaladsl.Producer
import akka.stream.scaladsl.Source
import com.datastax.driver.core.utils.UUIDs
import com.david.phantomtest.infrastructure.database.MyDatabase
import com.david.phantomtest.infrastructure.kafka.KafkaProducer
import com.david.phantomtest.user.User
import com.outworkers.phantom.dsl.ResultSet
import org.apache.kafka.clients.producer.ProducerRecord

object Main extends App {

  import com.david.phantomtest.infrastructure.DefaultImplicits._

  val done = Source(1 to 10)
    .map( n => User(UUIDs.random(), "User" + n))
    .mapAsync( 1 ){ MyDatabase.users.store }
    .map { user =>
      new ProducerRecord[Array[Byte], String]("test", user + " created")
    }
    .runWith(Producer.plainSink(KafkaProducer.producerSettings, KafkaProducer.producer))

  //  val store = MyDatabase.users.store(User(UUIDs.random(), "Mauricio"))
  //  store.onComplete( println )
}
