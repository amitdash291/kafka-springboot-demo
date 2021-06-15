package com.example.springboot_service_demo.producers

import com.example.springboot_service_demo.configurations.KafkaIntegration
import com.example.springboot_service_demo.exceptions.KafkaIntegrationDisabledException
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.mockk.mockk
import io.mockk.verify
import org.springframework.kafka.core.KafkaTemplate

class PingProducerTest: DescribeSpec({
    describe("PingProducer") {
        val mockKafkaTemplate = mockk<KafkaTemplate<String, String>>(relaxed = true)

        it("should send a message when config is enabled") {
            val expectedMessage = "ping"
            val pingProducer = PingProducer(mockKafkaTemplate, KafkaIntegration(enabled  = true))
            pingProducer.sendMessage(expectedMessage)
            verify { mockKafkaTemplate.send("ping-topic", expectedMessage) }
        }

        it("should NOT send a message when config is disabled") {
            val ex = shouldThrow<KafkaIntegrationDisabledException> {
                val pingProducer = PingProducer(mockKafkaTemplate, KafkaIntegration(enabled = false))
                pingProducer.sendMessage("ping")
            }
            ex.message shouldBe null
        }
    }
})