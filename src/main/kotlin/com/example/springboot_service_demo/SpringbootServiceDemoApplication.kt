package com.example.springboot_service_demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class SpringbootServiceDemoApplication

fun main(args: Array<String>) {
	runApplication<SpringbootServiceDemoApplication>(*args)
}