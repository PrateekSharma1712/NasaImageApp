package com.example.nasaapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nasaapp.framework.DataLoader
import com.example.nasaapp.model.ImageModel

class ImageListViewModel : ViewModel() {

    private val dataLoader: DataLoader = DataLoader()
    var imagesLiveData = MutableLiveData<List<ImageModel>>()

    init {
        dataLoader.fetchImageData().let {
            imagesLiveData.value = it
        }
    }
}