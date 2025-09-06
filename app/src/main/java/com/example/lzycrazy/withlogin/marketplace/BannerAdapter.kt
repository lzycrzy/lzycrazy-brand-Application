package com.example.lzycrazy.withlogin.marketplace

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.lzycrazy.R
import com.google.android.material.imageview.ShapeableImageView

class BannerAdapter(
    private var marketPosts: List<Any>,
    private val onItemClick: (Any) -> Unit
) : RecyclerView.Adapter<BannerAdapter.BannerViewHolder>() {

    companion object {
        private const val TYPE_IMAGE_POST = 0
        private const val TYPE_MARKET_POST = 1
    }

    fun updatePosts(newPosts: List<Any>) {
        marketPosts = newPosts
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return if (marketPosts[position] is MarketPost) TYPE_MARKET_POST else TYPE_IMAGE_POST
    }

    inner class BannerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ShapeableImageView = view.findViewById(R.id.ivBannerImage)
        val title: TextView? = view.findViewById(R.id.tvPropertyTitle)
        val price: TextView? = view.findViewById(R.id.tvPropertyPrice)
        val postedBy: TextView? = view.findViewById(R.id.tvPostedBy)
        val location: TextView? = view.findViewById(R.id.tvLocation)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClick(marketPosts[position])
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        val layoutRes = when (viewType) {
            TYPE_MARKET_POST -> R.layout.item_property_banner
            else -> R.layout.marketplace_image_banner
        }
        val view = LayoutInflater.from(parent.context).inflate(layoutRes, parent, false)
        return BannerViewHolder(view)
    }

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
        when (val item = marketPosts[position]) {
            is MarketPost -> bindMarketPost(holder, item)
            is ImagePost -> bindImagePost(holder, item)
        }
    }

    private fun bindMarketPost(holder: BannerViewHolder, post: MarketPost) {
        if (post.images.isNotEmpty()) {
            Glide.with(holder.imageView.context)
                .load(post.images[0])
                .placeholder(R.drawable.ic_real_estate_banner)
                .into(holder.imageView)
        } else {
            holder.imageView.setImageResource(R.drawable.ic_real_estate_banner)
        }

        holder.title?.text = post.title
        holder.price?.text = "₹${post.price}"
        holder.postedBy?.text = "By: ${post.postedBy.name}"
        holder.location?.text = "${post.location.neighbourhood}, ${post.location.city}"
    }

    private fun bindImagePost(holder: BannerViewHolder, post: ImagePost) {
        Glide.with(holder.imageView.context)
            .load(post.postUrl)
            .placeholder(R.drawable.ic_real_estate_banner)
            .into(holder.imageView)
    }

    override fun getItemCount(): Int = marketPosts.size
}