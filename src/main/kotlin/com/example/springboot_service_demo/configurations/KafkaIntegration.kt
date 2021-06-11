package com.example.springboot_service_demo.configurations

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties("kafka-integration")
data class KafkaIntegration(val enabled: Boolean)