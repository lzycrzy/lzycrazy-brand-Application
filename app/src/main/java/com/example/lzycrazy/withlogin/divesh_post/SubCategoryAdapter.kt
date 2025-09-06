package com.example.lzycrazy.withlogin.divesh_post

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lzycrazy.R


class SubCategoryAdapter(
    private var subCategories: List<String>
) : RecyclerView.Adapter<SubCategoryAdapter.SubCategoryViewHolder>() {

    inner class SubCategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvSubCategory: TextView = itemView.findViewById(R.id.tvSubCategory)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubCategoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.ad_subcategory, parent, false)
        return SubCategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: SubCategoryViewHolder, position: Int) {
        holder.tvSubCategory.text = subCategories[position]
    }

    override fun getItemCount() = subCategories.size

    fun updateData(newList: List<String>) {
        subCategories = newList
        notifyDataSetChanged()
    }
}
