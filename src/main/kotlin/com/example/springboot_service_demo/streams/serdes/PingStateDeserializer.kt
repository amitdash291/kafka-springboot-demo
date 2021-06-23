package com.example.springboot_service_demo.streams.serdes

import com.example.springboot_service_demo.common.SerializationUtil
import com.example.springboot_service_demo.models.PingState
import org.apache.kafka.common.serialization.Deserializer

class PingStateDeserializer : Deserializer<PingState> {
    override fun deserialize(topic: String?, data: ByteArray?) =
        SerializationUtil.deserializeDefault(data, PingState())
}