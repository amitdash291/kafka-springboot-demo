package com.example.springboot_service_demo.streams.serdes

import com.example.springboot_service_demo.common.SerializationUtil
import com.example.springboot_service_demo.models.PingUpdated
import org.apache.kafka.common.serialization.Deserializer

class PingUpdatedDeserializer : Deserializer<PingUpdated> {
    override fun deserialize(topic: String?, data: ByteArray?) =
        SerializationUtil.deserializeDefault(data, PingUpdated())
}