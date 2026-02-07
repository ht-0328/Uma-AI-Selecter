package com.example.demo.news.entity

import java.time.LocalDateTime

data class NewsItem(
    val id: Long? = null,
    val title: String,
    val content: String,
    val publishedAt: LocalDateTime,
    val createdAt: LocalDateTime = LocalDateTime.now()
)
