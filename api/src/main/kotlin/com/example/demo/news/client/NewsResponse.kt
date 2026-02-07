package com.example.demo.news.client

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime

data class NewsResponse(
    val title: String,
    val content: String,
    @JsonProperty("published_at")
    val publishedAt: LocalDateTime
)
