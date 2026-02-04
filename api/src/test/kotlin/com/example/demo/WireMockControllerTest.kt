package com.example.demo

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest
import org.springframework.http.MediaType
import org.springframework.test.web.client.MockRestServiceServer
import org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo
import org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess

@RestClientTest(WireMockController::class, properties = ["app.wiremock.base-url=http://test-wiremock"])
class WireMockControllerTest {

    @Autowired
    private lateinit var server: MockRestServiceServer

    @Autowired
    private lateinit var controller: WireMockController

    @Test
    fun `ping should call wiremock and return response`() {
        // 準備
        val expectedJson = """{"ok": true}"""
        server.expect(requestTo("http://test-wiremock/mock/ping"))
            .andRespond(withSuccess(expectedJson, MediaType.APPLICATION_JSON))

        // 実行
        val result = controller.ping()

        // 検証
        assertEquals(mapOf("ok" to true), result)
    }
}
