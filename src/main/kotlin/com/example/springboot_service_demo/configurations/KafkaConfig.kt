package com.example.springboot_service_demo.configurations

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties("spring.kafka")
data class KafkaConfig(val bootstrapServers: String)