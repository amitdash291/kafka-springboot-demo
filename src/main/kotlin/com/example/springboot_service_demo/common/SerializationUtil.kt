package com.example.springboot_service_demo.common

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object SerializationUtil {
    inline fun <reified T> serializeDefault(data: T?) =
        data?.let { Json.encodeToString<T>(it) }
            .orEmpty()
            .toByteArray()

    inline fun <reified T> deserializeDefault(data: ByteArray?, default: T): T {
        val nonNullData = data ?: return default
        return try {
            val stringJson = String(nonNullData)
            Json.decodeFromString(stringJson)
        } catch (_: Exception) {
            default
        }
    }
}