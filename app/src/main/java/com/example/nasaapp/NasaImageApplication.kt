package com.example.nasaapp

import android.app.Application
import android.content.Context

class NasaImageApplication : Application() {

    init {
        instance = this
    }

    companion object {
        private var instance: NasaImageApplication? = null

        fun getApplicationContext(): Context {
            return instance!!.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        val context = NasaImageApplication.getApplicationContext()
    }
}