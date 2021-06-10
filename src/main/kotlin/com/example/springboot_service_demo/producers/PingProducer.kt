package com.example.springboot_service_demo.producers

import org.slf4j.LoggerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class PingProducer(
    private val kafkaTemplate: KafkaTemplate<String, String>
) {
    private val logger = LoggerFactory.getLogger(PingProducer::class.java)

    fun sendMessage(message: String) {
        this.kafkaTemplate.send("ping-topic", message)
        this.logger.info("Message sent to ping-topic");
    }
}