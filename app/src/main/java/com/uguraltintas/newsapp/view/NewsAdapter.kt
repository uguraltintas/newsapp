package com.uguraltintas.newsapp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.uguraltintas.newsapp.R
import com.uguraltintas.newsapp.model.Article
import com.uguraltintas.newsapp.model.News


class NewsAdapter(
    private var newsList : List<Article>,
    val listener : OnItemClickListener
    ) : RecyclerView.Adapter<NewsAdapter.NewsVH>() {

    inner class NewsVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val newsImage = itemView.findViewById<ImageView>(R.id.newsImage)
        val newsDescription = itemView.findViewById<TextView>(R.id.newsDescription)
        val newsPublishedDate = itemView.findViewById<TextView>(R.id.newsPublishedDate)
        val newsAuthor = itemView.findViewById<TextView>(R.id.newsAuthor)
        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION ) {
                    listener.onClick(getData(position))
                    }
                }
            }
        }


    interface OnItemClickListener{
        fun onClick(article: Article)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_recycler_row,parent,false)
        return NewsVH(view)
    }

    override fun onBindViewHolder(holder: NewsVH, position: Int) {
        var url = newsList[position].urlToImage
        Glide
            .with(holder.newsImage)
            .load(url)
            .centerCrop()
            .into(holder.newsImage);
        holder.newsDescription.text = newsList[position].description
        holder.newsPublishedDate.text = newsList[position].publishedAt
        holder.newsAuthor.text = newsList[position].source.name
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    fun setNewsList(newList: List<Article>) {
        newsList = newList
        notifyDataSetChanged()
    }

    fun getData(pos : Int): Article{
        return newsList[pos]
    }
}