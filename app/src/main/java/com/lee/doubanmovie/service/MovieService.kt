package com.lee.doubanmovie.service

import com.lee.doubanmovie.data.Movie
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {
    @GET("j/search_subjects")
    fun getMovies(@Query("type") type: String,
                  @Query("tag") tag: String,
                  @Query("page_limit") limit: Int,
                  @Query("page_start") start: Int): Observable<Movie>
}