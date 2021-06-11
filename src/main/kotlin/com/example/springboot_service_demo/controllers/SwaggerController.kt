package com.example.springboot_service_demo.controllers

import io.swagger.v3.oas.annotations.Hidden
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.view.RedirectView

@RestController
@Hidden
class SwaggerController {
    @GetMapping("swagger")
    fun getSwaggerUi() = RedirectView("/swagger-ui.html")
}