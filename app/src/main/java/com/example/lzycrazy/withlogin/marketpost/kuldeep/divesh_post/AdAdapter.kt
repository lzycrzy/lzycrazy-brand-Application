package com.example.lzycrazy.withlogin.marketpost.kuldeep.divesh_post

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lzycrazy.R


class AdAdapter(
    private val categories: List<String>,
    private val onCategoryClick: (Int) -> Unit
) : RecyclerView.Adapter<AdAdapter.CategoryViewHolder>() {

    private var selectedPosition = 0

    inner class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvCategory: TextView = itemView.findViewById(R.id.tvCategory)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.ad_category, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.tvCategory.text = categories[position]

        holder.itemView.isSelected = selectedPosition == position

        holder.itemView.setOnClickListener {
            val adapterPos = holder.bindingAdapterPosition
            if (adapterPos != RecyclerView.NO_POSITION) {
                val previous = selectedPosition
                selectedPosition = adapterPos
                notifyItemChanged(previous)
                notifyItemChanged(adapterPos)
                onCategoryClick(adapterPos)
            }
        }
    }


    override fun getItemCount() = categories.size
}
