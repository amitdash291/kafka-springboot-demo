package com.example.springboot_service_demo.common

import com.example.springboot_service_demo.models.PingStatus
import com.example.springboot_service_demo.models.PingUpdated
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

internal class SerializationUtilTest : DescribeSpec({
    describe("SerializationUtil") {
        it("should deserialize object from a byte array") {
            val jsonToDeserialize = "{\"pingId\":133, \"status\":\"NEW\"}"
            val expected = PingUpdated(
                pingId = 133,
                status = PingStatus.NEW
            )
            val actual = SerializationUtil.deserializeDefault(
                jsonToDeserialize.toByteArray(),
                PingUpdated()
            )
            actual.shouldBe(expected)
        }
        it("should serialize object to a string") {
            val expected = "{\"pingId\":1143,\"status\":\"DELIVERED\"}"
                .toByteArray()
            val actual = SerializationUtil.serializeDefault(
                PingUpdated(
                    pingId = 1143,
                    status = PingStatus.DELIVERED
                )
            )
            actual.shouldBe(expected)
        }
    }
})