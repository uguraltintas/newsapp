package com.uguraltintas.newsapp.retrofit

import com.uguraltintas.newsapp.model.News
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RetrofitService {

    @GET("top-headlines?country=tr&apiKey=2a1043ba6d434653b222eaddc40f97ce")
    fun getNewsData() : Call<News>

    companion object {
        private val baseUrl = " https://newsapi.org/v2/"
        var retrofitService: RetrofitService? = null

        fun getInstance() : RetrofitService {

            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(RetrofitService::class.java)
            }
            return retrofitService!!
        }
    }
}