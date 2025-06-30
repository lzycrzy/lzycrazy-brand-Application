package com.example.lzycrazy

import android.os.Bundle
import android.widget.ExpandableListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MarketplaceActivity : AppCompatActivity() {

    private lateinit var expandableListView: ExpandableListView
    private lateinit var categoryMap: HashMap<String, List<String>>
    private lateinit var categoryList: List<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_marketplace)

        expandableListView = findViewById(R.id.expandableListView)
        setupCategories()
    }

    private fun setupCategories() {
        categoryList = listOf(
            "Vehicles", "Real Estate", "Electronics", "Fashion", "Jobs",
            "Services", "Pets", "Furniture", "Books"
        )

        categoryMap = hashMapOf(
            "Vehicles" to listOf("Car", "Bike", "Bus"),
            "Real Estate" to listOf("Flat","Plot","Villa"),
            "Electronics" to listOf("Mobiles", "Laptops", "Televisions", "Cameras", "Appliances"),
            "Fashion" to listOf("Men's Clothing", "Women's Clothing", "Kid's Fashion", "Footwear", "Accessories"),
            "Jobs" to listOf("Full-time", "Part-time", "Internship", "Freelance", "Government Jobs"),
            "Services" to listOf("Repair Services", "Home Services", "Tutoring", "Event Management", "Legal Services"),
            "Pets" to listOf("Dogs", "Cats", "Birds", "Fish", "Pet Accessories"),
            "Furniture" to listOf("Sofa", "Bed", "Table", "Chair", "Wardrobe"),
            "Books" to listOf("Fiction", "Non-fiction", "Textbooks", "Children's Books", "Magazines")
        )

        val adapter = CategoryExpandableAdapter(this, categoryList, categoryMap)
        expandableListView.setAdapter(adapter)

        expandableListView.setOnChildClickListener { _, _, groupPosition, childPosition, _ ->
            val group = categoryList[groupPosition]
            val child = categoryMap[group]?.get(childPosition)
            Toast.makeText(this, "$group > $child clicked", Toast.LENGTH_SHORT).show()
            true
        }
    }
}
