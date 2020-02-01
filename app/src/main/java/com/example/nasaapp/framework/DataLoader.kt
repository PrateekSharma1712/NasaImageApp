package com.example.nasaapp.framework

import android.content.Context
import com.example.nasaapp.model.ImageModel
import com.google.gson.Gson
import java.io.IOException
import java.io.InputStream
import java.nio.charset.Charset

class DataLoader {

    fun fetchImageData(context: Context, fileName: String): List<ImageModel> {
        val jsonString = loadJsonFromAsset(context, fileName)
        val gson = Gson()
        return gson.fromJson(jsonString, Array<ImageModel>::class.java).asList()
    }

    private fun loadJsonFromAsset(context: Context, fileName: String): String? {
        return try {
            val inputStream: InputStream? =
                context.assets?.open(fileName)
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