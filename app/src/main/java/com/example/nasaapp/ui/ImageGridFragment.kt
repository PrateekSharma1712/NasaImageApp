package com.example.nasaapp.ui


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.nasaapp.R
import kotlinx.android.synthetic.main.fragment_image_grid.*

/**
 * A simple [Fragment] subclass.
 */
class ImageGridFragment : Fragment(R.layout.fragment_image_grid), ImageAdapter.ImageClickListener {

    private lateinit var imagesViewModel: ImageListViewModel
    private lateinit var imageAdapter: ImageAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        imagesViewModel = ViewModelProvider(activity!!).get(ImageListViewModel::class.java)
        imageAdapter = ImageAdapter(this)
        initializeObservers()
    }

    private fun initializeObservers() {
        imagesViewModel.imagesLiveData.observe(activity!!, Observer {
            imagesRecyclerView.apply {
                adapter = imageAdapter
                setHasFixedSize(true)
            }
            imageAdapter.submitList(imagesViewModel.imagesLiveData.value)
            imagesRecyclerView.visibility = View.VISIBLE
            noNetworkImageView.visibility = View.GONE

        })

        imagesViewModel.isConnectedToInternet.observe(activity!!, Observer {
            noNetworkImageView.visibility = View.VISIBLE
            imagesRecyclerView.visibility = View.GONE
        })
    }

    override fun onImageClicked(index: Int) {
        imagesViewModel.onImageSelected(index)
        findNavController().navigate(ImageGridFragmentDirections.actionImageClicked())
    }
}