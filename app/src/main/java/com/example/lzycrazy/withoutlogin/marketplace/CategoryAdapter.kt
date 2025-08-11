package com.example.lzycrazy.withoutlogin.marketplace

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.lzycrazy.R
import com.example.lzycrazy.withoutlogin.marketplace.SubCategoryAdapter
import com.google.android.material.imageview.ShapeableImageView
class CategoryAdapter(
    private val list: List<Category>,
    private val onCategoryClicked: (Category) -> Unit,
    private val onSubCategoryClicked: (String, String) -> Unit
) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    private var expandedPosition = -1

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val icon: ShapeableImageView = view.findViewById(R.id.ivCategoryIcon)
        val name: TextView = view.findViewById(R.id.tvCategoryTitle)
        val subCategoryList: RecyclerView = view.findViewById(R.id.rvSubCategoryList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_category, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category = list[position]

        // Bind category name and icon
        holder.name.text = category.name
        Glide.with(holder.icon.context)
            .load(category.imageData.url)
            .placeholder(R.drawable.ic_real_estate)
            .into(holder.icon)

        // Expand/collapse subcategories
        val isExpanded = position == expandedPosition
        holder.subCategoryList.visibility = if (isExpanded) View.VISIBLE else View.GONE

        // Setup subcategory list
        holder.subCategoryList.layoutManager = LinearLayoutManager(holder.itemView.context)
        holder.subCategoryList.adapter = SubCategoryAdapter(category.subcategories) { subcategory ->
            onSubCategoryClicked(category._id, subcategory.name)
        }

        holder.itemView.setOnClickListener {
            val previousExpandedPosition = expandedPosition
            val wasExpanded = isExpanded

            expandedPosition = if (wasExpanded) -1 else position
            notifyItemChanged(previousExpandedPosition)
            notifyItemChanged(position)

            // Only trigger onCategoryClicked if a new category is being expanded
            if (!wasExpanded) {
                onCategoryClicked(category)
            }
        }
    }

    override fun getItemCount(): Int = list.size
}
