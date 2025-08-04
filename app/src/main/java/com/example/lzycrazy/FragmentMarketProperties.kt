package com.example.lzycrazy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

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