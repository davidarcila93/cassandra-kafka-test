package com.david.phantomtest.infrastructure.kafka

import akka.kafka.ConsumerSettings
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.{ByteArrayDeserializer, StringDeserializer}

object KafkaConsumer {
  import com.david.phantomtest.infrastructure.DefaultImplicits._

  val consumerSettings: ConsumerSettings[Array[Byte], String] = ConsumerSettings(system, new ByteArrayDeserializer, new StringDeserializer)
    .withBootstrapServers("localhost:9092")
    .withGroupId("group1")
    .withProperty(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true")
}