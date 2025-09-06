package com.example.lzycrazy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class PropertyListingFragment : DialogFragment() {

    // Data model for properties
    data class PropertyModel(
        val title: String,
        val address: String,
        val isForSale: Boolean
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate your fragment layout containing RecyclerView with id rvPropertyList
        return inflater.inflate(R.layout.fragment_property_listing, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Create dummy data list with 10 items
        val propertyList = List(10) { index ->
            PropertyModel(
                title = "Property #$index",
                address = "Address of Property #$index",
                isForSale = index % 2 == 0 // alternate For Sale flag
            )
        }

        // Setup RecyclerView
        val recyclerView = view.findViewById<RecyclerView>(R.id.rvPropertyList)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 1)

        recyclerView.adapter = object : RecyclerView.Adapter<PropertyViewHolder>() {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PropertyViewHolder {
                val itemView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_property, parent, false)
                return PropertyViewHolder(itemView)
            }

            override fun onBindViewHolder(holder: PropertyViewHolder, position: Int) {
                holder.bind(propertyList[position])
            }

            override fun getItemCount() = propertyList.size
        }
    }

    // ViewHolder class
    inner class PropertyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        private val tvAddress: TextView = itemView.findViewById(R.id.tvAddress)
        private val tvForSale: TextView = itemView.findViewById(R.id.tvForSale)

        fun bind(property: PropertyModel) {
            tvTitle.text = property.title
            tvAddress.text = property.address
            tvForSale.text = if (property.isForSale) "FOR SALE" else "FOR RENT"
        }
    }
}
