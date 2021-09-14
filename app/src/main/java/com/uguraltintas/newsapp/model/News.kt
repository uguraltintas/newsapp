package com.uguraltintas.newsapp.model


import com.google.gson.annotations.SerializedName

data class News(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)