package com.example.lzycrazy.not_in_currently_used

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.lzycrazy.not_in_currently_used.MarketplacePropertyItem
import com.example.lzycrazy.R

class FriendAdapter1(private val friendList1: List<Friend1>) :
    RecyclerView.Adapter<FriendAdapter1.FriendViewHolder>() {

    inner class FriendViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tvName)
        val tvMutual: TextView = itemView.findViewById(R.id.tvMutual)
        val tvLocation: TextView = itemView.findViewById(R.id.tvLocation)
        val btnFriends: Button = itemView.findViewById(R.id.btnFriends)
        val btnMessage: Button = itemView.findViewById(R.id.btnMessage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_friend_card, parent, false)
        return FriendViewHolder(view)
    }

    override fun onBindViewHolder(holder: FriendViewHolder, position: Int) {
        val friend = friendList1[position]
        holder.tvName.text = friend.name
        holder.tvMutual.text = friend.mutualFriends
        holder.tvLocation.text = friend.location
        // Handle clicks if needed
    }

    override fun getItemCount(): Int = friendList1.size
}

class MarketplacePropertiesAdapter(private val items: List<MarketplacePropertyItem>) :
    RecyclerView.Adapter<MarketplacePropertiesAdapter.PropertyViewHolder>() {

    class PropertyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.propertyImage)
        val price: TextView = itemView.findViewById(R.id.propertyPrice)
        val location: TextView = itemView.findViewById(R.id.propertyLocation)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PropertyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_property_card, parent, false)
        return PropertyViewHolder(view)
    }

    override fun onBindViewHolder(holder: PropertyViewHolder, position: Int) {
        val item = items[position]
        holder.price.text = item.price
        holder.location.text = item.location
        holder.image.setImageResource(item.imageRes)
    }

    override fun getItemCount(): Int = items.size
}

// Not sure whether its the part of the marketplace without login or not
data class MarketplacePropertyItem(
    val price: String,
    val location: String,
    val imageRes: Int
)

class ThumbnailAdapter(
    private val images: List<Int>,
    private val onClick: (Int) -> Unit
) : RecyclerView.Adapter<ThumbnailAdapter.ThumbViewHolder>() {

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

data class PropertyItem(
    val price: String,
    val location: String,
    val imageRes: Int
)