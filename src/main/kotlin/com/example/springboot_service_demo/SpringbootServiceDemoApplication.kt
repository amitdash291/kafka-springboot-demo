package com.example.springboot_service_demo

import com.example.springboot_service_demo.configurations.KafkaIntegration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
@EnableConfigurationProperties(KafkaIntegration::class)
class SpringbootServiceDemoApplication

fun main(args: Array<String>) {
	runApplication<SpringbootServiceDemoApplication>(*args)
}
