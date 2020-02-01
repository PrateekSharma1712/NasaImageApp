package com.example.nasaapp.ui

import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.example.nasaapp.R
import com.example.nasaapp.model.ImageModel
import kotlinx.android.synthetic.main.line_item_image.view.*


class ImageAdapter :
    ListAdapter<ImageModel, ImageAdapter.ImageViewHolder>(DiffCallback) {

    val requestOptions = RequestOptions()
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .override(200, 200)

    inner class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(imageModel: ImageModel) {
            Glide.with(itemView.context).load(imageModel.url).apply(requestOptions)
                .transition(
                    DrawableTransitionOptions.withCrossFade()
                ).listener(object : RequestListener<Drawable> {
                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        itemView.imageLoadingProgressBar.visibility = View.GONE
                        itemView.imageView.visibility = View.VISIBLE
                        return false
                    }

                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        itemView.imageView.setImageResource(R.drawable.ic_unable_to_download)
                        return false
                    }

                }).into(itemView.imageView)

            itemView.titleTextView.text = imageModel.title
            itemView.setOnClickListener {
                Log.d("Image Model", imageModel.title!!)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.line_item_image,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val imageModel = getItem(position)
        holder.bind(imageModel)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<ImageModel>() {
        override fun areItemsTheSame(oldItem: ImageModel, newItem: ImageModel): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: ImageModel, newItem: ImageModel): Boolean {
            return oldItem == newItem
        }

    }
}