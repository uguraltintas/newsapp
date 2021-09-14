package com.uguraltintas.newsapp.repository

import com.uguraltintas.newsapp.retrofit.RetrofitService

class MainRepository(private val retrofitService: RetrofitService) {

    fun getNewsData() = retrofitService.getNewsData()
}