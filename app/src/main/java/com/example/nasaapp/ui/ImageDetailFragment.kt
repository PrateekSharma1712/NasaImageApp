package com.example.nasaapp.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.example.nasaapp.R
import kotlinx.android.synthetic.main.fragment_image_detail.*

/**
 * A simple [Fragment] subclass.
 */
class ImageDetailFragment : Fragment(R.layout.fragment_image_detail) {

    private lateinit var imageViewModel: ImageListViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        imageViewModel = ViewModelProvider(activity!!).get(ImageListViewModel::class.java)
        val adapter = ImageSlidePageAdapter((activity as ImageListActivity).supportFragmentManager)
        imagesViewPager.adapter = adapter
        imagesViewPager.currentItem = imageViewModel.selectedImageIndex
        imagesViewPager.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                (activity as ImageListActivity).updateImageDetailTitle(position)
            }
        })
    }

    private inner class ImageSlidePageAdapter(fragmentManager: FragmentManager) :
        FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
        override fun getItem(position: Int): Fragment {
            return ImageDetailPagerFragment(position)
        }

        override fun getCount(): Int {
            return imageViewModel.imagesLiveData.value?.size ?: 0
        }

    }
}