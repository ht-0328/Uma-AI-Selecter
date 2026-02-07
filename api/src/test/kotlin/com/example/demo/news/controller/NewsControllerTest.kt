package com.example.demo.news.controller

import com.example.demo.config.SecurityConfig
import com.example.demo.news.entity.NewsItem
import com.example.demo.news.service.NewsService
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.annotation.Import
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.time.LocalDateTime

@WebMvcTest(NewsController::class)
@Import(SecurityConfig::class)
class NewsControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var newsService: NewsService

    @Test
    fun `sync should return 200`() {
        mockMvc.perform(post("/api/news/sync"))
            .andExpect(status().isOk)
    }

    @Test
    fun `getAll should return list`() {
        val mockNews = listOf(
            NewsItem(
                id = 1L,
                title = "Test",
                content = "Content",
                publishedAt = LocalDateTime.now()
            )
        )
        Mockito.`when`(newsService.getAllNews()).thenReturn(mockNews)

        mockMvc.perform(get("/api/news"))
            .andExpect(status().isOk)
    }
}
