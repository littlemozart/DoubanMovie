package com.lee.doubanmovie

import android.app.Application
import kotlin.properties.Delegates

class DoubanApp : Application() {
    companion object {
        var instance: Application by Delegates.notNull()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}