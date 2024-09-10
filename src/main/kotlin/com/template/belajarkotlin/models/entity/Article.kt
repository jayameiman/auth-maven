package com.template.belajarkotlin.models.entity

import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank

@Entity
@Table(name = "article")
data class Article(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @get : NotBlank
    val title: String = "",

    @get: NotBlank
    val description: String = "",

    @get: NotBlank
    val content: String = "")
