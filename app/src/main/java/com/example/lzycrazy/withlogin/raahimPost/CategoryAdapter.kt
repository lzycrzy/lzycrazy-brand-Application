package com.example.lzycrazy.withlogin.raahimPost

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lzycrazy.R

class CategoryAdapter(
    private val categories: List<Category>,
    private val onSubcategoryClick: (categoryName: String, subcategoryName: String) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val TYPE_CATEGORY = 0
        private const val TYPE_SUBCATEGORY = 1
    }

    override fun getItemViewType(position: Int): Int {
        var index = 0
        categories.forEach { cat ->
            if (position == index) return TYPE_CATEGORY
            index++
            if (cat.isExpanded) {
                if (position < index + cat.subcategories.size) return TYPE_SUBCATEGORY
                index += cat.subcategories.size
            }
        }
        return TYPE_CATEGORY
    }

    override fun getItemCount(): Int {
        var count = categories.size
        categories.forEach { if (it.isExpanded) count += it.subcategories.size }
        return count
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == TYPE_CATEGORY) {
            val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_category_parentrow_dropdown, parent, false)
            CategoryVH(v)
        } else {
            val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_subcategory_post, parent, false)
            SubcategoryVH(v)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var index = 0
        categories.forEach { category ->
            // Parent row
            if (position == index && holder is CategoryVH) {
                holder.bind(category)
                holder.itemView.setOnClickListener {
                    category.isExpanded = !category.isExpanded
                    notifyDataSetChanged()
                }
                return
            }
            index++

            // Child rows when expanded
            if (category.isExpanded) {
                category.subcategories.forEach { sub ->
                    if (position == index && holder is SubcategoryVH) {
                        holder.bind(sub)
                        holder.itemView.setOnClickListener {
                            onSubcategoryClick(category.name, sub)
                        }
                        return
                    }
                    index++
                }
            }
        }
    }

    private class CategoryVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val name: TextView = itemView.findViewById(R.id.categoryName)
        private val expandIcon: ImageView = itemView.findViewById(R.id.expandIcon)
        private val icon: ImageView = itemView.findViewById(R.id.categoryIcon)

        fun bind(category: Category) {
            name.text = category.name
            // icons are coming from API later → keep view but hide for now
            icon.visibility = View.GONE
            expandIcon.rotation = if (category.isExpanded) 90f else 0f
        }
    }

    private class SubcategoryVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val subName: TextView = itemView.findViewById(R.id.subCategoryName)
        fun bind(text: String) {
            subName.text = text
        }
    }
}
