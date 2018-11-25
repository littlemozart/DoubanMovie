package com.lee.doubanmovie.main

import android.util.Log
import com.lee.doubanmovie.data.MovieRepository
import io.reactivex.disposables.CompositeDisposable

class MoviePresenter(private val movieView: MovieContract.View, private val tag: String) : MovieContract.Presenter {

    private var mDisposables: CompositeDisposable
    private var mStart = 0
    private var isLoadingMore = false
    private var isRefreshing = false

    init {
        movieView.presenter = this
        mDisposables = CompositeDisposable()
    }

    override fun start() {
        mStart = 0
        request()
    }

    override fun refresh() {
        isRefreshing = true
        mStart = 0
        request()
    }

    override fun loadMore() {
        isLoadingMore = true
        request()
    }

    override fun request() {
        mDisposables.add(
            MovieRepository.getMovieList(tag, mStart)
                .subscribe({ result ->
                    when {
                        isRefreshing -> {
                            isRefreshing = false
                            movieView.setMovieList(result.subjects)
                            movieView.onRefreshFinished(true)
                        }
                        isLoadingMore -> {
                            isLoadingMore = false
                            movieView.addMovieList(result.subjects)
                            movieView.onLoadMoreFinished(true)
                        }
                        else -> {
                            movieView.setMovieList(result.subjects)
                            if (result.subjects.isEmpty()) {
                                movieView.showEmpty()
                            } else {
                                movieView.showContent()
                            }
                        }
                    }
                    mStart += result.subjects.size
                    movieView.setNoMoreData(result.subjects.isEmpty())
                }, { error ->
                    when {
                        isLoadingMore -> {
                            isLoadingMore = false
                            movieView.onLoadMoreFinished(false)
                        }
                        isRefreshing -> {
                            isRefreshing = false
                            movieView.onRefreshFinished(false)
                        }
                        else -> {
                            movieView.showError()
                        }
                    }
                    Log.e("Kotlin", "error", error)
                }, {
                    movieView.showContent()
                    Log.d("Kotlin", "onComplete")
                }, {
                    if (!isRefreshing && !isLoadingMore) {
                        movieView.showLoading()
                    }
                    Log.d("Kotlin", "onSubscribe")
                })
        )
    }

    override fun release() {
        mDisposables.dispose()
        mDisposables.clear()
    }
}