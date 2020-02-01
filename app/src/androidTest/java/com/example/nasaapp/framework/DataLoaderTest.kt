package com.example.nasaapp.framework

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.nasaapp.model.ImageModel
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DataLoaderTest1 {

    private val dataLoader = DataLoader()
    private lateinit var context: Context

    @Before
    fun setUp() {
        context = ApplicationProvider.getApplicationContext()
    }

    @Test
    fun testFetchImageData() {
        val inputFileName = "data.json"
        val imageModelList = dataLoader.fetchImageData(context, inputFileName)
        val imageModel = imageModelList[0]

        Assert.assertNotNull(imageModelList)
        Assert.assertTrue(imageModelList.isNotEmpty())
        Assert.assertTrue(imageModel is ImageModel)
    }
}