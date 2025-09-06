package com.example.lzycrazy.not_in_currently_used

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lzycrazy.not_in_currently_used.PropertiesAdapter
import com.example.lzycrazy.not_in_currently_used.PropertyItem
import com.example.lzycrazy.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentMarketProperties.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentMarketProperties : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: PropertiesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_market_properties, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerViewServices)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)

        val sampleList = listOf(
            PropertyItem("₹ 1,80,00,000", "3 BHK, 1000 sqft", R.drawable.sample_property1),
            PropertyItem("₹ 90,00,000", "2 BHK, 750 sqft", R.drawable.sample_property2),
            PropertyItem("₹ 70,00,000", "1 BHK, 500 sqft", R.drawable.sample_property3),
            PropertyItem("₹ 2,10,00,000", "4 BHK, 1500 sqft", R.drawable.sample_property4),
        )

        adapter = PropertiesAdapter(sampleList)
        recyclerView.adapter = adapter
    }
}

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

class SliderAdapter(private val images: List<Int>) : RecyclerView.Adapter<SliderAdapter.SliderViewHolder>() {

    inner class SliderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.sliderImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_slider_image, parent, false)
        return SliderViewHolder(view)
    }

    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
        holder.imageView.setImageResource(images[position])
    }

    override fun getItemCount(): Int = images.size
}