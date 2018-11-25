package com.lee.doubanmovie.main

import com.lee.doubanmovie.base.BasePresenter
import com.lee.doubanmovie.base.BaseView
import com.lee.doubanmovie.data.Movie

interface MovieContract {
    interface View : BaseView<Presenter> {
        fun onRefreshFinished(success: Boolean)
        fun onLoadMoreFinished(success: Boolean)
        fun showLoading()
        fun showEmpty()
        fun showError()
        fun showContent()
        fun setMovieList(list: List<Movie.Subject>)
        fun addMovieList(list: List<Movie.Subject>)
        fun setNoMoreData(noMore: Boolean)
    }

    interface Presenter : BasePresenter {
        fun refresh()
        fun loadMore()
        fun request()
    }
}