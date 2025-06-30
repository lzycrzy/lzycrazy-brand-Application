package com.example.lzycrazy

import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2

class LzyMarketplaceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lzy_marketplace)

        // First slider images
        val sliderImages1 = listOf(
            R.drawable.slider1,
            R.drawable.slider2,
            R.drawable.slider3
        )

        // Second slider images (placeholder used for now)
        val sliderImages2 = listOf(
            R.drawable.slider4,
            R.drawable.placeholder, // if you don't have slider5/6, use placeholder
            R.drawable.placeholder
        )

        // Bind viewpager
        val imageSlider1 = findViewById<ViewPager2>(R.id.imageSlider1)
        val imageSlider2 = findViewById<ViewPager2>(R.id.imageSlider2)

        imageSlider1.adapter = ImageSliderAdapter(sliderImages1)
        imageSlider2.adapter = ImageSliderAdapter(sliderImages2)

        // Optional: Set click listeners on video thumbnails
        val videoThumbnail = findViewById<ImageView>(R.id.videoThumbnail)
        val videoThumbnail2 = findViewById<ImageView>(R.id.videoThumbnail2)

        videoThumbnail.setOnClickListener {
            Toast.makeText(this, "Play video 1", Toast.LENGTH_SHORT).show()
        }

        videoThumbnail2.setOnClickListener {
            Toast.makeText(this, "Play video 2", Toast.LENGTH_SHORT).show()
        }
    }
}
