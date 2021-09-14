package com.uguraltintas.newsapp.repository

import com.uguraltintas.newsapp.retrofit.RetrofitService

class MainRepository(private val retrofitService: RetrofitService) {

    fun getNewsData(country : String , apiKey : String) = retrofitService.getNewsData(country, apiKey)
}