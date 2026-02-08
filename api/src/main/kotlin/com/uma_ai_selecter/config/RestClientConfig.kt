package com.uma_ai_selecter.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestClient

/**
 * RESTクライアントの設定
 */
@Configuration
class RestClientConfig(
    @Value("\${app.wiremock.base-url}") private val wireMockBaseUrl: String
) {

    /**
     * WireMock用のRestClientを生成する
     */
    @Bean
    fun wireMockRestClient(): RestClient {
        return RestClient.builder()
            .baseUrl(wireMockBaseUrl)
            .build()
    }
}
