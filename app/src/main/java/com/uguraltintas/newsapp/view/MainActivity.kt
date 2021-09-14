package com.uguraltintas.newsapp.view

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.uguraltintas.newsapp.R
import com.uguraltintas.newsapp.model.Article
import com.uguraltintas.newsapp.repository.MainRepository
import com.uguraltintas.newsapp.retrofit.RetrofitService
import com.uguraltintas.newsapp.viewmodel.MainViewModel
import com.uguraltintas.newsapp.viewmodel.MainViewModelFactory

class MainActivity : AppCompatActivity(),NewsAdapter.OnItemClickListener {
    lateinit var viewModel: MainViewModel
    private val retrofitService = RetrofitService.getInstance()
    lateinit var recyclerView: RecyclerView
    lateinit var swipeRefresh : SwipeRefreshLayout
    val adapter = NewsAdapter(arrayListOf(),this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this, MainViewModelFactory(MainRepository(retrofitService))).get(MainViewModel::class.java)
        viewModel.newsDataList.observe(this, Observer {
            adapter.setNewsList(it.articles)
        })
        viewModel.errorMessage.observe(this, Observer {
            print(it)
        })

        viewModel.getWeatherData(country,apiKey)

        recyclerView = findViewById(R.id.newsRecyclerView)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        swipeRefresh = findViewById<SwipeRefreshLayout>(R.id.swipeRefresh)
        swipeRefresh.setOnRefreshListener {
            viewModel.getWeatherData(country,apiKey)
            swipeRefresh.isRefreshing = false
        }

    }

    override fun onClick(article: Article) {
        val url = article.url
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)

    }

    companion object{
        val country = "tr"
        val apiKey = "YOUR-API-KEY"
    }

}