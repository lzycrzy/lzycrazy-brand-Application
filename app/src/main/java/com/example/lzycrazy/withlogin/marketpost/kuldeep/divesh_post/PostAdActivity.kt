package com.example.lzycrazy.withlogin.marketpost.kuldeep.divesh_post

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lzycrazy.R


class PostAdActivity : AppCompatActivity() {

    private lateinit var adAdapter: AdAdapter
    private lateinit var subCategoryAdapter: SubCategoryAdapter

    private val categoryMap = mapOf(
        "Properties" to listOf("Buy", "Rent", "Commercial for Sale", "Commercial for Lease"),
        "Direct By Farm" to listOf("Anaaj/Grain","Vegetables","Fruits","Milk","Fish"),
        "Self Made" to listOf("Handicrafts", "Art", "Others"),
        "Vehicle" to listOf("Cars", "Bikes", "Commercial Vehicles"),
        "Mobiles" to listOf("Smartphones", "Accessories"),
        "Electro Store" to listOf("TV", "Fridge", "Washing Machine"),
        "Fashion Zone" to listOf("Men", "Women", "Kids"),
        "Furniture" to listOf("Living Room", "Bedroom", "Office")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.post_ad)

        val categories = categoryMap.keys.toList()
        val rvCategories = findViewById<RecyclerView>(R.id.rvCategories)
        val rvSubCategories = findViewById<RecyclerView>(R.id.rvSubCategories)

        rvCategories.layoutManager = LinearLayoutManager(this)
        rvSubCategories.layoutManager = LinearLayoutManager(this)

        subCategoryAdapter = SubCategoryAdapter(categoryMap[categories[0]] ?: emptyList())
        rvSubCategories.adapter = subCategoryAdapter

        adAdapter = AdAdapter(categories) { pos ->
            val selectedCategory = categories[pos]
            subCategoryAdapter.updateData(categoryMap[selectedCategory] ?: emptyList())
        }

        rvCategories.adapter = adAdapter
    }
}
