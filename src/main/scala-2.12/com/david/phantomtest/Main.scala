package com.david.phantomtest

import akka.kafka.Subscriptions
import akka.kafka.scaladsl.{Consumer, Producer}
import akka.stream.scaladsl.{Sink, Source}
import com.datastax.driver.core.utils.UUIDs
import com.david.phantomtest.infrastructure.kafka.{KafkaConsumer, KafkaProducer}
import com.david.phantomtest.user.User
import com.david.phantomtest.user.repository.UserRepositoryCassandra
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.TopicPartition

import scala.concurrent.Future

object Main extends App {

  import com.david.phantomtest.infrastructure.DefaultImplicits._

  val userRepo = new UserRepositoryCassandra

  val subscription = Subscriptions.assignment( new TopicPartition("test", 0) )
  val consumer =
    Consumer.plainSource(KafkaConsumer.consumerSettings, subscription)
      .mapAsync(1)( record => { println(record); Future.successful(record) } )
      .runWith(Sink.ignore)

  val done = Source(1 to 10)
    .map( n => User(UUIDs.random(), "User" + n))
    .mapAsync( 1 ){ userRepo.store }
    .map { user =>
      new ProducerRecord[Array[Byte], String]("test", user + " created")
    }
    .runWith(Producer.plainSink(KafkaProducer.producerSettings, KafkaProducer.producer))
}
