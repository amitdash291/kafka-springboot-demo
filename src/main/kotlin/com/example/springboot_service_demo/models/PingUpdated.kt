package com.example.springboot_service_demo.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PingUpdated(
    @SerialName("pingId")
    val pingId: Int = 0,
    @SerialName("status")
    val status: PingStatus = PingStatus.NONE
)