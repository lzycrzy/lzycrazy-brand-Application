package com.example.lzycrazy.withoutlogin.marketplace

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.lzycrazy.ImageSliderAdapter
import com.example.lzycrazy.R
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator

class MarketplacePropertyImagesFragment : Fragment(R.layout.fragment_marketplace_property_images) {

    private lateinit var viewPager: ViewPager2
    private lateinit var dotsIndicator: DotsIndicator
    private lateinit var leftArrow: ImageView
    private lateinit var rightArrow: ImageView

    private val imageList = listOf(
        R.drawable.ic_real_estate_banner,
        R.drawable.ic_real_estate_banner,
        R.drawable.ic_real_estate_banner,
        R.drawable.ic_real_estate_banner,
        R.drawable.ic_real_estate_banner
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewPager = view.findViewById(R.id.imageViewPager)
        dotsIndicator = view.findViewById(R.id.dots_indicator)
        leftArrow = view.findViewById(R.id.leftArrow)
        rightArrow = view.findViewById(R.id.rightArrow)

        viewPager.adapter = ImageSliderAdapter(imageList)
        dotsIndicator.attachTo(viewPager)

        leftArrow.setOnClickListener {
            viewPager.currentItem = (viewPager.currentItem - 1).coerceAtLeast(0)
        }
        rightArrow.setOnClickListener {
            viewPager.currentItem = (viewPager.currentItem + 1)
                .coerceAtMost(imageList.size - 1)
        }
    }
}