package com.example.lzycrazy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2

class MarketFragment : Fragment() {

    private lateinit var videoThumbnail1: ImageView
    private lateinit var videoThumbnail2: ImageView
    private lateinit var imageSlider1: ViewPager2
    private lateinit var imageSlider2: ViewPager2


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate your layout
        return inflater.inflate(R.layout.fragment_market, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize image views
        videoThumbnail1 = view.findViewById(R.id.videoThumbnail)
        videoThumbnail2 = view.findViewById(R.id.videoThumbnail2)

        // Subcategory Layouts
        val subCars = view.findViewById<View>(R.id.subcategoriesCars)
        val subProperties = view.findViewById<View>(R.id.subcategoriesProperties)

        // Category Buttons
        val btnCars = view.findViewById<View>(R.id.btnCars)
        val btnProperties = view.findViewById<View>(R.id.btnProperties)
        val btnMobiles = view.findViewById<View>(R.id.btnMobiles)

        // Toggle logic
        btnCars.setOnClickListener {
            subCars.visibility = if (subCars.visibility == View.VISIBLE) View.GONE else View.VISIBLE
            subProperties.visibility = View.GONE
        }

        btnProperties.setOnClickListener {
            subProperties.visibility = if (subProperties.visibility == View.VISIBLE) View.GONE else View.VISIBLE
            subCars.visibility = View.GONE
        }

        // Video click listeners
        videoThumbnail1.setOnClickListener {
            Toast.makeText(requireContext(), "Play video 1", Toast.LENGTH_SHORT).show()
        }

        videoThumbnail2.setOnClickListener {
            Toast.makeText(requireContext(), "Play video 2", Toast.LENGTH_SHORT).show()
        }

        imageSlider1 = view.findViewById(R.id.imageSlider1)
        imageSlider2 = view.findViewById(R.id.imageSlider2)

        val sliderImages1 = listOf(R.drawable.slider1, R.drawable.slider2, R.drawable.slider3)
        val sliderImages2 = listOf(R.drawable.slider4, R.drawable.placeholder, R.drawable.placeholder)

        imageSlider1.adapter = ImageSliderAdapter(sliderImages1)
        imageSlider2.adapter = ImageSliderAdapter(sliderImages2)

    }
}
