package com.example.demo.news.repository

import com.example.demo.news.entity.NewsItem
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param

@Mapper
interface NewsItemMapper {
    fun insert(newsItem: NewsItem)
    fun existsByTitle(@Param("title") title: String): Boolean
    fun findAll(): List<NewsItem>
}
