package com.example.nasaapp.framework

import com.example.nasaapp.NasaImageApplication
import com.example.nasaapp.model.ImageModel
import com.google.gson.Gson
import java.io.IOException
import java.io.InputStream
import java.nio.charset.Charset

class DataLoader {

    fun fetchImageData(): List<ImageModel> {
        val jsonString = loadJsonFromAsset()
        val gson = Gson()
        return gson.fromJson(jsonString, Array<ImageModel>::class.java).asList()
    }

    private fun loadJsonFromAsset(): String? {
        return try {
            val inputStream: InputStream? =
                NasaImageApplication.application.assets?.open("data.json")
            val size = inputStream?.available()
            val buffer = ByteArray(size ?: 0)
            inputStream?.read(buffer)
            inputStream?.close()
            String(buffer, Charset.forName("UTF-8"))
        } catch (ex: IOException) {
            ex.printStackTrace()
            null
        }
    }
}