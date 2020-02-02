package com.example.nasaapp.ui

import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.NetworkCallback
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
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
    private lateinit var connectivityManager: ConnectivityManager
    private lateinit var networkRequest: NetworkRequest
    private lateinit var networkAvailabilityCallback: NetworkCallback

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_list)

        imagesViewModel = ViewModelProvider(this).get(ImageListViewModel::class.java)

        val navController = findNavController(R.id.navHostFragment)
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        toolbar.setupWithNavController(navController, appBarConfiguration)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.imageDetailFragment) {
                updateImageDetailTitle(imagesViewModel.selectedImageIndex)
            }
        }

        connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        networkRequest = NetworkRequest.Builder()
            .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI).build()

        networkAvailabilityCallback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                imagesViewModel.updateNetworkConnectivity(true)
            }

            override fun onLost(network: Network) {
                super.onLost(network)
                imagesViewModel.updateNetworkConnectivity(false)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        connectivityManager.registerNetworkCallback(networkRequest, networkAvailabilityCallback)
    }

    override fun onStop() {
        super.onStop()
        connectivityManager.unregisterNetworkCallback(networkAvailabilityCallback)
    }

    fun updateImageDetailTitle(position: Int) {
        toolbar.title = imagesViewModel.getImageTitle(position)
    }

}