package com.template.belajarkotlin.models.repository

import com.template.belajarkotlin.models.entity.Article
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ArticleRepository : JpaRepository<Article, Long> {
}