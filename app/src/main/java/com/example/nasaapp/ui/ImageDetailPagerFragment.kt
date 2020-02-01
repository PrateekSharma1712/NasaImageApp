package com.example.nasaapp.ui

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.nasaapp.R
import com.example.nasaapp.model.ImageModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_COLLAPSED
import com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_EXPANDED
import kotlinx.android.synthetic.main.fragment_image_detail_pager.*

class ImageDetailPagerFragment(private val position: Int) :
    Fragment(R.layout.fragment_image_detail_pager) {

    private lateinit var imageViewModel: ImageListViewModel
    private var imageModel: ImageModel? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        imageViewModel = ViewModelProvider(activity!!).get(ImageListViewModel::class.java)
        imageModel = imageViewModel.getImageModelByPosition(position)

        loadImage()
        initialiseBottomSheet()
    }

    private fun initialiseBottomSheet() {
        val sheetBehavior = BottomSheetBehavior.from(imageDetailBottomSheet)
        imageDetailBottomSheet.setOnClickListener {
            sheetBehavior.state =
                if (sheetBehavior.state == STATE_EXPANDED) STATE_COLLAPSED else STATE_EXPANDED
        }

        explanationTextView.text = imageModel?.explanation

    }

    private fun loadImage() {
        Glide.with(this).load(imageModel?.hdurl)
            .transition(DrawableTransitionOptions.withCrossFade()).listener(object :
                RequestListener<Drawable> {
                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    imageLoadingProgressBar.visibility = View.GONE
                    fullScreenImageView.visibility = View.VISIBLE
                    return false
                }

                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    fullScreenImageView.setImageResource(R.drawable.ic_unable_to_download)
                    return false
                }

            }).into(fullScreenImageView)
    }
}