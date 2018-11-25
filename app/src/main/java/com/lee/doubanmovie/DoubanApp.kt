package com.lee.doubanmovie

import android.app.Application
import com.squareup.leakcanary.LeakCanary
import kotlin.properties.Delegates

class DoubanApp : Application() {
    companion object {
        var instance: Application by Delegates.notNull()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return
        }
        LeakCanary.install(this)
    }
}