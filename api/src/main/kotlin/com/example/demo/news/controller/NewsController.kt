package com.example.demo.news.controller

import com.example.demo.news.api.NewsApi
import com.example.demo.news.model.NewsItem
import com.example.demo.news.service.NewsService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import com.example.demo.news.entity.NewsItem as NewsItemEntity

@RestController
class NewsController(
    private val newsService: NewsService
) : NewsApi {

    override fun syncNews(): ResponseEntity<Unit> {
        newsService.syncNews()
        return ResponseEntity.ok().build()
    }

    override fun getNews(): ResponseEntity<List<NewsItem>> {
        val entities = newsService.getAllNews()
        val items = entities.map { entity ->
            NewsItem(
                title = entity.title,
                content = entity.content,
                publishedAt = entity.publishedAt,
                id = entity.id,
                createdAt = entity.createdAt
            )
        }
        return ResponseEntity.ok(items)
    }
}
