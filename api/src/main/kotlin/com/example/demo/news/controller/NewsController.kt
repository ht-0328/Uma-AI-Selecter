package com.example.demo.news.controller

import com.example.demo.news.entity.NewsItem
import com.example.demo.news.service.NewsService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/news")
class NewsController(
    private val newsService: NewsService
) {

    @PostMapping("/sync")
    fun sync() {
        newsService.syncNews()
    }

    @GetMapping
    fun getAll(): List<NewsItem> {
        return newsService.getAllNews()
    }
}
