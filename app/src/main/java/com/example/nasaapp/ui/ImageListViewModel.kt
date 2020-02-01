package com.example.nasaapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nasaapp.framework.DataLoader
import com.example.nasaapp.model.ImageModel

class ImageListViewModel : ViewModel() {

    private val dataLoader: DataLoader = DataLoader()
    var imagesLiveData = MutableLiveData<List<ImageModel>>()
    var selectedImageIndex: Int = 0

    init {
        dataLoader.fetchImageData().let {
            imagesLiveData.value = it
        }
    }

    fun onImageSelected(index: Int) {
        selectedImageIndex = index
    }

    fun getImageTitle(position: Int) : String? = getImageModelByPosition(position)?.title

    fun getImageModelByPosition(position: Int): ImageModel? = imagesLiveData.value?.get(position)
}