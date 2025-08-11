package com.example.lzycrazy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lzycrazy.withoutlogin.marketplace.MarketplacePropertiesAdapter
import com.example.lzycrazy.withoutlogin.marketplace.MarketplacePropertyItem

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