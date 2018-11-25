package com.lee.doubanmovie.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lee.doubanmovie.R
import com.lee.doubanmovie.adapter.SimpleFragmentAdapter
import kotlinx.android.synthetic.main.activity_movie.*

class MovieActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)
        setSupportActionBar(toolbar)
        val titles = arrayOf("热门", "最新", "豆瓣高分")
        val fragment1 = MovieFragment.newInstance()
        val fragment2 = MovieFragment.newInstance()
        val fragment3 = MovieFragment.newInstance()
        val fragments = arrayOf(fragment1, fragment2, fragment3)
        view_pager.adapter = SimpleFragmentAdapter(supportFragmentManager, fragments.asList(), titles.asList())
        view_pager.offscreenPageLimit = titles.size - 1
        tab_layout.setupWithViewPager(view_pager)
        MoviePresenter(fragment1, titles[0])
        MoviePresenter(fragment2, titles[1])
        MoviePresenter(fragment3, titles[2])
    }
}
