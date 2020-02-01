package com.example.nasaapp.di

import com.example.nasaapp.NasaImageApplication
import com.example.nasaapp.framework.DataLoader
import com.example.nasaapp.ui.ImageListViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(viewModel: ImageListViewModel)
    fun inject(dataLoaded: DataLoader)

    fun getApplication(): NasaImageApplication
}