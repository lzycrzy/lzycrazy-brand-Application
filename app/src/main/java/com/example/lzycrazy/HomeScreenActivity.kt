package com.example.lzycrazy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomeScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)
        if (savedInstanceState == null) {
            val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.topNavigationFragmentContainer, TopNavigationFragment())
            fragmentTransaction.commit()
        }

        val recyclerView: RecyclerView = findViewById(R.id.rvStories)

        // Set the layout manager
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        // Create a simple adapter
        val adapter = object : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

            // Create a new view holder
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
                val view = LayoutInflater.from(this@HomeScreenActivity)
                    .inflate(R.layout.item_story, parent, false)
                return object : RecyclerView.ViewHolder(view) {}
            }

            // Bind data to the views (static in this case)
            override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
                val storyImage: ImageView = holder.itemView.findViewById(R.id.storyImage)
                val userName: TextView = holder.itemView.findViewById(R.id.userName)

                // Set image and name (using static values here)
                storyImage.setImageResource(R.drawable.story_person_icon)
                userName.text = "User $position"
            }

            // Return the size of the dataset (static, so we can say it's 10)
            override fun getItemCount(): Int = 10
        }

        // Set the adapter to RecyclerView
        recyclerView.adapter = adapter
    }
}

