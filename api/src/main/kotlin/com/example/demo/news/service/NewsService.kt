package com.example.demo.news.service

import com.example.demo.news.client.NewsClient
import com.example.demo.news.entity.NewsItem
import com.example.demo.news.repository.NewsItemMapper
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class NewsService(
    private val newsClient: NewsClient,
    private val newsItemMapper: NewsItemMapper
) {
    @Transactional
    fun syncNews() {
        val newsList = newsClient.fetchNews()

        val newItems = newsList.filter {
            !newsItemMapper.existsByTitle(it.title)
        }.map {
            NewsItem(
                title = it.title,
                content = it.content,
                publishedAt = it.publishedAt
            )
        }

        newItems.forEach { newsItemMapper.insert(it) }
    }

    fun getAllNews(): List<NewsItem> {
        return newsItemMapper.findAll()
    }
}
