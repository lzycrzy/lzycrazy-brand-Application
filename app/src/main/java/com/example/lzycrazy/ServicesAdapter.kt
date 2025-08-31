package com.example.lzycrazy

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ServicesAdapter(private val items: List<ServicesAdapterItem>) :
    RecyclerView.Adapter<ServicesAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivImage: ImageView = itemView.findViewById(R.id.ivServicesImage)
        val tvTitle: TextView = itemView.findViewById(R.id.tvServicesTitle)
        val tvDescription: TextView = itemView.findViewById(R.id.tvServicesDescription)
        val tvComingSoon: TextView = itemView.findViewById(R.id.tvComingSoon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_business_card, parent, false)
        return ViewHolder(view)
    }

    // In ServicesAdapter.kt


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        val context = holder.itemView.context

        // Check if this is the last item in the list and set visibility accordingly for the coming soon cardview
        if (position == items.size - 1) {

            holder.tvTitle.text = "Coming Soon"
            holder.tvTitle.textSize = 22f

            holder.ivImage.visibility = View.VISIBLE
            Glide.with(context)
                .load(R.drawable.ic_coming_soon)
                .placeholder(R.drawable.ic_services_img)
                .error(R.drawable.ic_services_img)
                .into(holder.ivImage)

            holder.tvDescription.visibility = View.VISIBLE
            holder.tvDescription.text = "Exciting new service on the way!"
            holder.tvComingSoon.visibility = View.GONE

        } else {


            holder.tvTitle.text = item.title
            holder.tvDescription.text = item.description
            holder.tvTitle.textSize = 20f

            holder.ivImage.visibility = View.VISIBLE
            holder.tvDescription.visibility = View.VISIBLE

            // 3. Load the normal service image
            Glide.with(context)
                .load(item.icon.component)
                .placeholder(R.drawable.ic_services_img)
                .error(R.drawable.ic_services_img)
                .into(holder.ivImage)
            holder.tvComingSoon.visibility = View.GONE
        }
    }

    override fun getItemCount(): Int = items.size
}
