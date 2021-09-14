package com.uguraltintas.newsapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.uguraltintas.newsapp.model.News
import com.uguraltintas.newsapp.repository.MainRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainViewModel(private val repository: MainRepository) : ViewModel(){

    val newsDataList = MutableLiveData<News>()
    val errorMessage = MutableLiveData<String>()

    fun getWeatherData() {
        val response = repository.getNewsData()
        response.enqueue(object : Callback<News> {
            override fun onResponse(
                call: Call<News>,
                response: Response<News>
            ) {
                newsDataList.postValue(response.body())
            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                errorMessage.postValue(t.message)
            }

        })
    }
}