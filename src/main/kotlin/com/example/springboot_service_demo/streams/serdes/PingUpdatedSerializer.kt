package com.example.springboot_service_demo.streams.serdes

import com.example.springboot_service_demo.common.SerializationUtil
import com.example.springboot_service_demo.models.PingUpdated
import org.apache.kafka.common.serialization.Serializer

class PingUpdatedSerializer : Serializer<PingUpdated> {
    override fun serialize(topic: String?, data: PingUpdated?) =
        SerializationUtil.serializeDefault(data)
}