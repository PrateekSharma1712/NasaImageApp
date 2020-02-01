package com.example.nasaapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.nasaapp.R
import kotlinx.android.synthetic.main.activity_image_list.*

class ImageListActivity : AppCompatActivity() {

    private lateinit var imagesViewModel: ImageListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_list)

        imagesViewModel = ViewModelProvider(this).get(ImageListViewModel::class.java)

        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        toolbar.setupWithNavController(navController, appBarConfiguration)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.imageDetailFragment) {
                updateImageDetailTitle(imagesViewModel.selectedImageIndex)
            }
        }
    }

    fun updateImageDetailTitle(position: Int) {
        toolbar.title = imagesViewModel.getImageTitle(position)
    }

}