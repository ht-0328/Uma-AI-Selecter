package com.example.demo.news.repository

import com.example.demo.news.entity.NewsItem
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface NewsItemRepository : JpaRepository<NewsItem, Long> {
    fun existsByTitle(title: String): Boolean
}
