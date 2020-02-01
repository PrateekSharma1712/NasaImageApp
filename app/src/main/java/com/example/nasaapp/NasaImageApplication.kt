package com.example.nasaapp

import android.app.Application
import android.content.Context

class NasaImageApplication : Application() {

    companion object {
        @get:Synchronized
        lateinit var application: NasaImageApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()
        application = this
    }
}