package com.example.springboot_service_demo.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import java.lang.Exception

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE, reason = "Kafka integration is disabled")
class KafkaIntegrationDisabledException: Exception()