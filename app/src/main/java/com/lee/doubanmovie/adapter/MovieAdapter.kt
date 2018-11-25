package com.lee.doubanmovie.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bumptech.glide.Glide
import com.lee.doubanmovie.R
import com.lee.doubanmovie.data.Movie
import com.lee.doubanmovie.widget.RatioFixedImageView

class MovieAdapter(private val context: Context?) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    var list: MutableList<Movie.Subject>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    fun add(list: List<Movie.Subject>) {
        if (this.list == null) {
            this.list = ArrayList()
        }
        this.list?.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MovieViewHolder {
        return MovieViewHolder(LayoutInflater.from(context).inflate(R.layout.item_movie, p0, false))
    }

    override fun getItemCount(): Int = list?.size ?: 0

    override fun onBindViewHolder(p0: MovieViewHolder, p1: Int) {
        p0.text.text = list!![p1].title
        p0.rating.text = list!![p1].rate
        Glide.with(context!!).load(list!![p1].cover).into(p0.image)
        p0.itemView.setOnClickListener {

        }
    }

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val text: TextView = itemView.findViewById(R.id.text)
        val rating: TextView = itemView.findViewById(R.id.rating)
        val image: RatioFixedImageView = itemView.findViewById(R.id.image)
    }
}