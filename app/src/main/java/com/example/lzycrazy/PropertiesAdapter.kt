package com.example.lzycrazy

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PropertiesAdapter(private val items: List<PropertyItem>) :
    RecyclerView.Adapter<PropertiesAdapter.PropertyViewHolder>() {

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
