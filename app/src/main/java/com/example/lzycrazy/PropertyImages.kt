package com.example.lzycrazy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2

class PropertyImagesFragment : Fragment(R.layout.fragment_property_images) {

    private lateinit var viewPager: ViewPager2
    private lateinit var thumbnailsRecyclerView: RecyclerView

    private val imageList = listOf(
        R.drawable.sample_property1,
        R.drawable.sample_property2,
        R.drawable.sample_property3,
        R.drawable.sample_property4,
        R.drawable.sample_property5
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewPager = view.findViewById(R.id.imageViewPager)
        thumbnailsRecyclerView = view.findViewById(R.id.imageThumbnailsRecyclerView)

        // Setup ViewPager
        viewPager.adapter = ImageSliderAdapter(imageList)

        // Setup thumbnails RecyclerView
        thumbnailsRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        thumbnailsRecyclerView.adapter = ThumbnailAdapter(imageList) { position ->
            viewPager.currentItem = position
        }
    }
}

