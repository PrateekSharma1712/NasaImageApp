package com.example.nasaapp

import android.app.Application
import com.example.nasaapp.di.AppComponent
import com.example.nasaapp.di.AppModule
import com.example.nasaapp.di.DaggerAppComponent

class NasaImageApplication : Application() {

    lateinit var appComponent: AppComponent

    private fun initAppComponent(app: NasaImageApplication): AppComponent {
        return DaggerAppComponent.builder().appModule(
            AppModule(
                app
            )
        ).build()
    }

    companion object {
        @get:Synchronized
        lateinit var application: NasaImageApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()
        application = this
        appComponent = initAppComponent(application)
    }
}