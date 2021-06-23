package com.example.springboot_service_demo.producers

import com.example.springboot_service_demo.configurations.KafkaIntegrationConfig
import com.example.springboot_service_demo.exceptions.KafkaIntegrationDisabledException
import com.example.springboot_service_demo.models.Constants
import org.slf4j.LoggerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class PingProducer(
    private val kafkaTemplate: KafkaTemplate<String, String>,
    private val kafkaIntegrationConfig: KafkaIntegrationConfig
) {
    private val logger = LoggerFactory.getLogger(PingProducer::class.java)

    fun sendMessage(message: String) {
        if (!kafkaIntegrationConfig.enabled) throw KafkaIntegrationDisabledException()
        this.kafkaTemplate.send(Constants.PING_TOPIC, message)
        this.logger.info("Message sent to ${Constants.PING_TOPIC}");
    }
}