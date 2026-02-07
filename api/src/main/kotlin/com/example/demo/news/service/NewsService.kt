package com.example.demo.news.service

import com.example.demo.news.client.NewsClient
import com.example.demo.news.entity.NewsItem
import com.example.demo.news.repository.NewsItemRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class NewsService(
    private val newsClient: NewsClient,
    private val newsItemRepository: NewsItemRepository
) {
    @Transactional
    fun syncNews() {
        val newsList = newsClient.fetchNews()

        val newItems = newsList.filter {
            !newsItemRepository.existsByTitle(it.title)
        }.map {
            NewsItem(
                title = it.title,
                content = it.content,
                publishedAt = it.publishedAt
            )
        }

        if (newItems.isNotEmpty()) {
            newsItemRepository.saveAll(newItems)
        }
    }

    fun getAllNews(): List<NewsItem> {
        return newsItemRepository.findAll()
    }
}
