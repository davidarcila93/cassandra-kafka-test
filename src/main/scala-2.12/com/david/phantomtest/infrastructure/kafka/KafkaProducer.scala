package com.david.phantomtest.infrastructure.kafka

import akka.kafka.ProducerSettings
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.common.serialization.{ByteArraySerializer, StringSerializer}

object KafkaProducer {
  import com.david.phantomtest.infrastructure.DefaultImplicits._

  val producerSettings: ProducerSettings[Array[Byte], String] = ProducerSettings(system, new ByteArraySerializer, new StringSerializer)
    .withBootstrapServers( "localhost:9092" )

  val producer: KafkaProducer[Array[Byte], String] = producerSettings.createKafkaProducer()
}
