package com.example.demo.news.client

import org.springframework.beans.factory.annotation.Value
import org.springframework.core.ParameterizedTypeReference
import org.springframework.stereotype.Component
import org.springframework.web.client.RestClient

@Component
class NewsClient(
    restClientBuilder: RestClient.Builder,
    @Value("\${app.wiremock.base-url}") private val wireMockBaseUrl: String
) {
    private val restClient = restClientBuilder.baseUrl(wireMockBaseUrl).build()

    fun fetchNews(): List<NewsResponse> {
        return restClient.get()
            .uri("/external/news")
            .retrieve()
            .body(object : ParameterizedTypeReference<List<NewsResponse>>() {})
            ?: emptyList()
    }
}
