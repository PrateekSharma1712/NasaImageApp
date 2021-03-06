package com.example.nasaapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nasaapp.NasaImageApplication
import com.example.nasaapp.framework.DataLoader
import com.example.nasaapp.model.ImageModel
import javax.inject.Inject

class ImageListViewModel : ViewModel() {

    @Inject
    lateinit var dataLoader: DataLoader
    var imagesLiveData = MutableLiveData<List<ImageModel>>()
    var selectedImageIndex: Int = 0
    val isNetworkConnected = MutableLiveData<Boolean>()

    init {
        NasaImageApplication.application.appComponent.inject(this)
        loadNasaData()
    }

    private fun loadNasaData() {
        dataLoader.fetchImageData(NasaImageApplication.application, "data.json").let {
            imagesLiveData.postValue(it)
        }
    }

    fun onImageSelected(index: Int) {
        selectedImageIndex = index
    }

    fun getImageTitle(position: Int): String? = getImageModelByPosition(position)?.title

    fun getImageModelByPosition(position: Int): ImageModel? = imagesLiveData.value?.get(position)

    fun updateNetworkConnectivity(isConnected: Boolean) {
        isNetworkConnected.postValue(isConnected)
    }
}