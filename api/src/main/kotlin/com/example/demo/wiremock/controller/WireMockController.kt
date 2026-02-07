package com.example.demo.wiremock.controller

import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestClient

@RestController
class WireMockController(
    restClientBuilder: RestClient.Builder,
    @Value("\${app.wiremock.base-url}") wireMockBaseUrl: String
) {

    private val restClient = restClientBuilder.baseUrl(wireMockBaseUrl).build()

    @GetMapping("/wiremock/ping")
    fun ping(): Map<*, *>? {
        return restClient.get()
            .uri("/mock/ping")
            .retrieve()
            .body(Map::class.java)
    }
}
