package com.example.lzycrazy.withlogin.raahimPost

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lzycrazy.R

class PostFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CategoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_post, container, false)

        recyclerView = view.findViewById(R.id.recyclerCategories)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val categories = listOf(
            Category("Properties", listOf("Buy", "Rent", "Commercial for Sale", "Commercial for Lease")),
            Category("Direct By Farm", listOf("Vegetables", "Fruits", "Organic Products")),
            Category("Self Made", listOf("Handmade Crafts", "Paintings", "Decor Items")),
            Category("Vehicle", listOf("Cars", "Bikes", "Trucks", "Others")),
            Category("Mobiles", listOf("Smartphones", "Accessories", "Tablets")),
            Category("Electro Store", listOf("TV", "Laptops", "Appliances")),
            Category("Fashion Zone", listOf("Men", "Women", "Kids", "Accessories")),
            Category("Furniture", listOf("Sofa", "Bed", "Dining", "Chairs"))
        )

        adapter = CategoryAdapter(categories) { categoryName, subcategoryName ->
            // Navigate to a simple detail page (included below)
            parentFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, SelectedSubcategoryFragment.newInstance(categoryName, subcategoryName))
                .addToBackStack(null)
                .commit()
        }

        recyclerView.adapter = adapter
        return view
    }
}
