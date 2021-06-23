package com.example.springboot_service_demo.producers

import com.example.springboot_service_demo.configurations.KafkaIntegrationConfig
import com.example.springboot_service_demo.exceptions.KafkaIntegrationDisabledException
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.core.spec.style.ExpectSpec
import io.kotest.core.spec.style.FreeSpec
import io.kotest.core.spec.style.FunSpec
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.core.spec.style.WordSpec
import io.kotest.matchers.shouldBe
import io.mockk.mockk
import io.mockk.verify
import org.springframework.kafka.core.KafkaTemplate

class PingProducerTest : DescribeSpec({
    describe("PingProducer") {
        val mockKafkaTemplate = mockk<KafkaTemplate<String, String>>(relaxed = true)

        it("should send a message when config is enabled") {
            val expectedMessage = "ping"
            val pingProducer = PingProducer(mockKafkaTemplate, KafkaIntegrationConfig(enabled = true))
            pingProducer.sendMessage(expectedMessage)
            verify { mockKafkaTemplate.send("ping-topic", expectedMessage) }
        }

        it("should NOT send a message when config is disabled") {
            val ex = shouldThrow<KafkaIntegrationDisabledException> {
                val pingProducer = PingProducer(mockKafkaTemplate, KafkaIntegrationConfig(enabled = false))
                pingProducer.sendMessage("ping")
            }
            ex.message shouldBe null
        }
    }
})

class PingProducerFreeSpecTest : FreeSpec({
    "PingProducer" - {
        val mockKafkaTemplate = mockk<KafkaTemplate<String, String>>(relaxed = true)
        "when config is enabled" - {
            "should send a message" {
                val expectedMessage = "ping"
                val pingProducer = PingProducer(mockKafkaTemplate, KafkaIntegrationConfig(enabled = true))
                pingProducer.sendMessage(expectedMessage)
                verify { mockKafkaTemplate.send("ping-topic", expectedMessage) }
            }
        }
    }
})

class PingProducerWordSpecTest : WordSpec({
    "PingProducer" When {
        val mockKafkaTemplate = mockk<KafkaTemplate<String, String>>(relaxed = true)
        "config is enabled" Should {
            val pingProducer = PingProducer(mockKafkaTemplate, KafkaIntegrationConfig(enabled = true))
            "send a message" {
                val expectedMessage = "ping"
                pingProducer.sendMessage(expectedMessage)
                verify { mockKafkaTemplate.send("ping-topic", expectedMessage) }
            }
        }
    }
})

class PingProducerBehaviorSpecTest : BehaviorSpec({
    Given("PingProducer") {
        val mockKafkaTemplate = mockk<KafkaTemplate<String, String>>(relaxed = true)
        When("config is enabled") {
            val pingProducer = PingProducer(mockKafkaTemplate, KafkaIntegrationConfig(enabled = true))
            Then("sends a message") {
                val expectedMessage = "ping"
                pingProducer.sendMessage(expectedMessage)
                verify { mockKafkaTemplate.send("ping-topic", expectedMessage) }
            }
        }
    }
})

// ExpectSpec is similar to FunSpec / ShouldSpec
class PingProducerExpectSpecTest : ExpectSpec({
    context("PingProducer") {
        val mockKafkaTemplate = mockk<KafkaTemplate<String, String>>(relaxed = true)

        expect("should send a message when config is enabled") {
            val expectedMessage = "ping"
            val pingProducer = PingProducer(mockKafkaTemplate, KafkaIntegrationConfig(enabled = true))
            pingProducer.sendMessage(expectedMessage)
            verify { mockKafkaTemplate.send("ping-topic", expectedMessage) }
        }
    }
})

class PingProducerFunSpecTest : FunSpec({
    context("PingProducer") {
        val mockKafkaTemplate = mockk<KafkaTemplate<String, String>>(relaxed = true)

        test("should send a message when config is enabled") {
            val expectedMessage = "ping"
            val pingProducer = PingProducer(mockKafkaTemplate, KafkaIntegrationConfig(enabled = true))
            pingProducer.sendMessage(expectedMessage)
            verify { mockKafkaTemplate.send("ping-topic", expectedMessage) }
        }
    }
})

class PingProducerShouldSpecTest : ShouldSpec({
    context("PingProducer") {
        val mockKafkaTemplate = mockk<KafkaTemplate<String, String>>(relaxed = true)

        should("should send a message when config is enabled") {
            val expectedMessage = "ping"
            val pingProducer = PingProducer(mockKafkaTemplate, KafkaIntegrationConfig(enabled = true))
            pingProducer.sendMessage(expectedMessage)
            verify { mockKafkaTemplate.send("ping-topic", expectedMessage) }
        }
    }
})