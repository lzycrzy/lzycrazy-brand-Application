package com.example.lzycrazy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager

class ListingOfItemsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragement_listing_of_items, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Setup left text list
        val textRecyclerView: RecyclerView = view.findViewById(R.id.rvTextList)
        textRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        textRecyclerView.adapter = TextAdapter(createDummyTextData())

        // Setup right image grid
        val imageRecyclerView: RecyclerView = view.findViewById(R.id.rvImageGrid)
        imageRecyclerView.layoutManager = GridLayoutManager(requireContext(), 1) // 2 columns
        imageRecyclerView.adapter = ImageAdapter(createDummyImageData())
    }

    private fun createDummyTextData(): List<String> {
        return List(8) { "Properties" }
    }

    private fun createDummyImageData(): List<Int> {
        return listOf(
            R.drawable.ic_real_estate_banner,
            R.drawable.ic_real_estate_banner,
            R.drawable.ic_real_estate_banner,
            R.drawable.ic_real_estate_banner,
            R.drawable.ic_real_estate_banner,
            R.drawable.ic_real_estate_banner,
            R.drawable.ic_real_estate_banner,
            R.drawable.ic_real_estate_banner
        )
    }

    // Adapter for text list (left side)
    private inner class TextAdapter(private val items: List<String>) :
        RecyclerView.Adapter<TextAdapter.ViewHolder>() {

        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val textView: TextView = itemView.findViewById(R.id.tvItemText)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_property_listing_text, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.textView.text = items[position]
        }

        override fun getItemCount() = items.size
    }

    // Adapter for image grid (right side)
    private inner class ImageAdapter(private val imageResources: List<Int>) :
        RecyclerView.Adapter<ImageAdapter.ViewHolder>() {

        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val imageView: ImageView = itemView.findViewById(R.id.ivGridImage)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_property_listing_image, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.imageView.setImageResource(imageResources[position])
        }

        override fun getItemCount() = imageResources.size
    }
}