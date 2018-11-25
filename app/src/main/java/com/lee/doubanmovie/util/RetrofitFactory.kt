package com.lee.doubanmovie.util

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitFactory private constructor() {

    var retrofit: Retrofit = initRetrofit()

    private object Holder {
        val INSTANCE = RetrofitFactory()
    }

    companion object {
        val instance: RetrofitFactory by lazy { Holder.INSTANCE }
        private const val DEFAULT_TIMEOUT: Long = 15
        private const val BASE_URL: String = "https://movie.douban.com/"
    }

    private fun initRetrofit(): Retrofit {

        val client = OkHttpClient.Builder()
            .retryOnConnectionFailure(true)
            .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

}