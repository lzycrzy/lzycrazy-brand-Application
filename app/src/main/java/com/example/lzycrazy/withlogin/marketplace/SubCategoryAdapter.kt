package com.example.lzycrazy.withlogin.marketplace

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.lzycrazy.R
import com.google.android.material.imageview.ShapeableImageView

class SubCategoryAdapter(
    private val list: List<SubCategory>,
    private val onItemClicked: (SubCategory) -> Unit
) : RecyclerView.Adapter<SubCategoryAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val icon: ShapeableImageView = view.findViewById(R.id.ivSubCategoryIcon)
        val name: TextView = view.findViewById(R.id.tvSubCategoryName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_subcategory_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val sub = list[position]
        holder.name.text = sub.name
        Glide.with(holder.icon.context)
            .load(sub.imageData.url)
            .placeholder(R.drawable.ic_real_estate)
            .into(holder.icon)

        holder.itemView.setOnClickListener {
            onItemClicked(sub)
        }
    }

    override fun getItemCount(): Int = list.size
}