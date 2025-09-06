package com.example.lzycrazy.withoutlogin.services

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.lzycrazy.BusinessProfileDialogFragment
import com.example.lzycrazy.R

class ServicesAdapter(private val items: List<ServicesAdapterItem>) :
    RecyclerView.Adapter<ServicesAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivImage: ImageView = itemView.findViewById(R.id.ivServicesImage)
        val tvTitle: TextView = itemView.findViewById(R.id.tvServicesTitle)
        val tvDescription: TextView = itemView.findViewById(R.id.tvServicesDescription)
        val tvComingSoon: TextView = itemView.findViewById(R.id.tvComingSoon)
        val cardView: CardView = itemView.findViewById(R.id.cardView)  // CardView for click event
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_business_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        val context = holder.itemView.context

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

            // Load the normal service image
            Glide.with(context)
                .load(item.icon.component)
                .placeholder(R.drawable.ic_services_img)
                .error(R.drawable.ic_services_img)
                .into(holder.ivImage)
            holder.tvComingSoon.visibility = View.GONE
        }

        holder.cardView.setOnClickListener {
            val fragment = BusinessProfileDialogFragment()  // Instantiate your fragment
            fragment.show(
                (context as AppCompatActivity).supportFragmentManager,
                "BusinessProfileDialog"
            ) // Show the fragment
        }
    }

    override fun getItemCount(): Int = items.size
}
