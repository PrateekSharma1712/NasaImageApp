package com.example.nasaapp.di

import com.example.nasaapp.NasaImageApplication
import com.example.nasaapp.framework.DataLoader
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val app: NasaImageApplication) {

    @Provides
    @Singleton
    fun provideContext(): NasaImageApplication = app

    @Provides
    @Singleton
    fun provideDataLoader(): DataLoader = DataLoader()
}