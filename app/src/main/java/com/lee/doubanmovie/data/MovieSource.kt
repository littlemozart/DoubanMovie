package com.lee.doubanmovie.data

import io.reactivex.Observable

interface MovieSource {
    fun getMovieList(tag: String?, start: Int) : Observable<Movie>
}