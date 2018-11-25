package com.lee.doubanmovie.data

import com.lee.doubanmovie.service.MovieService
import com.lee.doubanmovie.util.RetrofitFactory
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object MovieRepository : MovieSource {
    private const val LIMIT = 10
    private const val TYPE = "movie"

    override fun getMovieList(tag: String?, start: Int): Observable<Movie> {
        return RetrofitFactory.instance.retrofit
            .create(MovieService::class.java)
            .getMovies(TYPE, tag ?: "", LIMIT, start)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}