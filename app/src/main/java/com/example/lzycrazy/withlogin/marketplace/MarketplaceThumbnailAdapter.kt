package com.example.lzycrazy.withlogin.marketplace

import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.lzycrazy.R

class MarketplaceThumbnailAdapter(
    private val images: List<Int>,
    private val onClick: (Int) -> Unit
) : RecyclerView.Adapter<MarketplaceThumbnailAdapter.ThumbViewHolder>() {

    private var selectedPosition = 0

    inner class ThumbViewHolder(val imageView: ImageView) : RecyclerView.ViewHolder(imageView) {
        fun bind(imageRes: Int, position: Int) {
            imageView.setImageResource(imageRes)

            // Apply selected border
            val context = imageView.context
            if (position == selectedPosition) {
                imageView.background = ContextCompat.getDrawable(context, R.drawable.thumb_selected_border)
            } else {
                imageView.background = ContextCompat.getDrawable(context, R.drawable.thumb_unselected_border)
            }

            imageView.setOnClickListener {
                val previousPosition = selectedPosition
                selectedPosition = position
                notifyItemChanged(previousPosition)
                notifyItemChanged(selectedPosition)
                onClick(position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThumbViewHolder {
        val imageView = ImageView(parent.context).apply {
            layoutParams = ViewGroup.LayoutParams(120, 120)
            scaleType = ImageView.ScaleType.CENTER_CROP
            setPadding(8, 8, 8, 8)
        }
        return ThumbViewHolder(imageView)
    }

    override fun onBindViewHolder(holder: ThumbViewHolder, position: Int) {
        holder.bind(images[position], position)
    }

    override fun getItemCount(): Int = images.size
}