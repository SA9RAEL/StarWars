package com.example.starwarssearch

import android.app.Application
import com.example.starwarssearch.di.AppComponent
import com.example.starwarssearch.di.DaggerAppComponent

class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().withContext(this).build()
    }

}