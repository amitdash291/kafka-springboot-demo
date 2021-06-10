package com.example.springboot_service_demo.consumers

import com.example.springboot_service_demo.producers.PingProducer
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.annotation.PartitionOffset
import org.springframework.kafka.annotation.TopicPartition
import org.springframework.stereotype.Component

@Component
class PingConsumer {
    private val logger = LoggerFactory.getLogger(PingProducer::class.java)

    @KafkaListener(
        topics = ["ping-topic"],
        groupId = "group-id",
        topicPartitions = [TopicPartition(
            topic = "ping-topic",
            partitionOffsets = [PartitionOffset(partition = "0", initialOffset = "0")]
        )]
    )
    fun consume(message: String) {
        this.logger.info("SUCCESS: Consumed ping message $message")
    }
}