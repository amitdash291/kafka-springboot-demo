package com.example.springboot_service_demo

import com.example.springboot_service_demo.configurations.KafkaConfig
import com.example.springboot_service_demo.configurations.KafkaIntegrationConfig
import com.example.springboot_service_demo.streams.CriticalPingsStream
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(
	KafkaConfig::class,
	KafkaIntegrationConfig::class
)
class SpringbootServiceDemoApplication(criticalPingsStream: CriticalPingsStream) {
	init {
	    criticalPingsStream.startUpdateStream()
	}
}

fun main(args: Array<String>) {
	runApplication<SpringbootServiceDemoApplication>(*args)
}
