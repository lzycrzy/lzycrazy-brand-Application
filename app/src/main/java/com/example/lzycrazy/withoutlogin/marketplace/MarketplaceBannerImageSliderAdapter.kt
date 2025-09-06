package com.example.lzycrazy.withoutlogin.marketplace

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.lzycrazy.R

class MarketplaceBannerImageSliderAdapter(private var images: List<String>) :
    RecyclerView.Adapter<MarketplaceBannerImageSliderAdapter.SliderViewHolder>() {

    inner class SliderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.sliderImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_marketplace_slider_image, parent, false)
        return SliderViewHolder(view)
    }

    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
        if (images.isNotEmpty() && position < images.size) {
            val requestOptions = RequestOptions()
                .transform(CenterCrop(), RoundedCorners(16))
                .placeholder(R.drawable.ic_real_estate_banner)
                .error(R.drawable.ic_real_estate_banner)

            Glide.with(holder.imageView.context)
                .load(images[position])
                .apply(requestOptions)
                .into(holder.imageView)
        }
    }

    override fun getItemCount(): Int = images.size

    fun updateImages(newImages: List<String>) {
        images = newImages
        notifyDataSetChanged()
    }
}