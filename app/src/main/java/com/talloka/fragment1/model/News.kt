package com.talloka.fragment1.model

data class News(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)