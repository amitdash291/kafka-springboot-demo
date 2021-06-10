package com.example.springboot_service_demo.controllers

import com.example.springboot_service_demo.producers.PingProducer
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("pings")
class PingController(private val pingProducer: PingProducer) {
    @PostMapping("/{pingMessage}")
    fun sendPing(@PathVariable pingMessage: String){
        this.pingProducer.sendMessage(pingMessage)
    }

    @GetMapping
    fun getPing() = "PONG"
}