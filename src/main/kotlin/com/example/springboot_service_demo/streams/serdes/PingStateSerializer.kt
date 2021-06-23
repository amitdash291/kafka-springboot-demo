package com.example.springboot_service_demo.streams.serdes

import com.example.springboot_service_demo.common.SerializationUtil
import com.example.springboot_service_demo.models.PingState
import org.apache.kafka.common.serialization.Serializer

class PingStateSerializer : Serializer<PingState> {
    override fun serialize(topic: String?, data: PingState?) =
        SerializationUtil.serializeDefault(data)
}