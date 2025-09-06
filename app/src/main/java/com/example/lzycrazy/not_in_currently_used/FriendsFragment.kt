package com.example.lzycrazy.not_in_currently_used

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lzycrazy.not_in_currently_used.MarketplacePropertiesAdapter
import com.example.lzycrazy.not_in_currently_used.MarketplacePropertyItem
import com.example.lzycrazy.R

class FriendsFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: FriendAdapter1
    private val friendList1 = listOf(
        Friend1(
            "Zeeshu Ali",
            "58 mutual friends, including Zeeshan Shareef and Moin Kassar",
            "Lives in Delhi, India"
        ),
        Friend1(
            "Zeeshu Ali",
            "58 mutual friends, including Zeeshan Shareef and Moin Kassar",
            "Lives in Delhi, India"
        ),
        Friend1(
            "Zeeshu Ali",
            "58 mutual friends, including Zeeshan Shareef and Moin Kassar",
            "Lives in Delhi, India"
        ),
        Friend1(
            "Zeeshu Ali",
            "58 mutual friends, including Zeeshan Shareef and Moin Kassar",
            "Lives in Delhi, India"
        )
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_friends, container, false)
        recyclerView = view.findViewById(R.id.friendsRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = FriendAdapter1(friendList1)
        recyclerView.adapter = adapter
        return view
    }
}

class PropertiesMarketplaceFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MarketplacePropertiesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_marketplace_properties, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.horizontalPropertiesRecyclerView)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 1)

        val sampleList = listOf(
            MarketplacePropertyItem(
                "₹ 1,80,00,000",
                "3 BHK, 1000 sqft",
                R.drawable.ic_real_estate_banner
            ),
            MarketplacePropertyItem(
                "₹ 90,00,000",
                "2 BHK, 750 sqft",
                R.drawable.ic_real_estate_banner
            ),
            MarketplacePropertyItem(
                "₹ 70,00,000",
                "1 BHK, 500 sqft",
                R.drawable.ic_real_estate_banner
            ),
            MarketplacePropertyItem(
                "₹ 2,10,00,000",
                "4 BHK, 1500 sqft",
                R.drawable.ic_real_estate_banner
            ),
        )

        adapter = MarketplacePropertiesAdapter(sampleList)
        recyclerView.adapter = adapter
    }
}

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