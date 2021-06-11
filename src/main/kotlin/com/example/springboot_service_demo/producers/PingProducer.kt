package com.example.springboot_service_demo.producers

import com.example.springboot_service_demo.configurations.KafkaIntegration
import com.example.springboot_service_demo.exceptions.KafkaIntegrationDisabledException
import org.slf4j.LoggerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class PingProducer(
    private val kafkaTemplate: KafkaTemplate<String, String>,
    private val kafkaIntegrationConfig: KafkaIntegration
) {
    private val logger = LoggerFactory.getLogger(PingProducer::class.java)

    fun sendMessage(message: String) {
        if(!kafkaIntegrationConfig.enabled) throw KafkaIntegrationDisabledException()
        this.kafkaTemplate.send("ping-topic", message)
        this.logger.info("Message sent to ping-topic");
    }
}