package com.example.lzycrazy.withlogin.marketplace

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.lzycrazy.R

class MarketplaceBannerImagesFragment : Fragment(R.layout.fragment_marketplace_banner_images) {

    private lateinit var viewPager: ViewPager2
    private lateinit var leftArrow: ImageView
    private lateinit var rightArrow: ImageView
    private lateinit var adapter: MarketplaceBannerImageSliderAdapter

    private var imageUrls: List<String> = emptyList()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewPager = view.findViewById(R.id.imageViewPager)
        leftArrow = view.findViewById(R.id.leftArrow)
        rightArrow = view.findViewById(R.id.rightArrow)

        // Initialize adapter with empty list
        adapter = MarketplaceBannerImageSliderAdapter(emptyList())
        viewPager.adapter = adapter

        // Set up page change callback to update arrow visibility
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                updateArrowVisibility()
            }
        })

        // Update with any initial URLs
        arguments?.getStringArrayList("imageUrls")?.let {
            updateImageUrls(it.toList())
        }

        leftArrow.setOnClickListener {
            viewPager.currentItem = (viewPager.currentItem - 1).coerceAtLeast(0)
        }
        rightArrow.setOnClickListener {
            viewPager.currentItem = (viewPager.currentItem + 1).coerceAtMost(imageUrls.size - 1)
        }
    }

    fun updateImageUrls(newImageUrls: List<String>) {
        imageUrls = newImageUrls
        adapter.updateImages(newImageUrls)

        // Reset to first image
        if (newImageUrls.isNotEmpty()) {
            viewPager.currentItem = 0
        }

        // Update arrow visibility based on number of images
        updateArrowVisibility()
    }

    private fun updateArrowVisibility() {
        if (imageUrls.size <= 1) {
            leftArrow.visibility = View.GONE
            rightArrow.visibility = View.GONE
        } else {
            leftArrow.visibility = View.VISIBLE
            rightArrow.visibility = View.VISIBLE

            // Update individual arrow visibility based on current position
            if (viewPager.currentItem == 0) {
                leftArrow.visibility = View.GONE
                rightArrow.visibility = View.VISIBLE
            } else if (viewPager.currentItem == imageUrls.size - 1) {
                leftArrow.visibility = View.VISIBLE
                rightArrow.visibility = View.GONE
            } else {
                leftArrow.visibility = View.VISIBLE
                rightArrow.visibility = View.VISIBLE
            }
        }
    }
}