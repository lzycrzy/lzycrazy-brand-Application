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
import de.hdodenhof.circleimageview.CircleImageView

class HomeScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)

        // For the fragment (ensuring that fragment does not shown multiple times
        if (savedInstanceState == null) {
            val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.topNavigationFragmentContainer, TopNavigationFragment())
            fragmentTransaction.commit()
        }

      // Using the recycler view for the Profile or status of the different persons
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

                storyImage.setImageResource(R.drawable.story_person_icon)
                userName.text = "User $position"
            }
            // Return the size of the dataset
            override fun getItemCount(): Int = 10
        }

        // Set the adapter to RecyclerView
        recyclerView.adapter = adapter

        // Using the recycler view for displaying posts
        val recyclerViewPost: RecyclerView = findViewById(R.id.rvPost)

        // Set the layout manager
        recyclerViewPost.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        // Create a simple adapter for posts
        val adapterPost = object : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

            // Create a new view holder for the post layout
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
                val view = LayoutInflater.from(this@HomeScreenActivity)
                    .inflate(R.layout.item_post, parent, false)
                return object : RecyclerView.ViewHolder(view) {}
            }

            // Bind data to the views (static in this case)
            override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
                // Distinct names for post adapter
                val postProfileImage: CircleImageView = holder.itemView.findViewById(R.id.profile_image)
                val postUserName: TextView = holder.itemView.findViewById(R.id.user_name)
                val postDate: TextView = holder.itemView.findViewById(R.id.post_date)
                val postDescription: TextView = holder.itemView.findViewById(R.id.post_description)
                val postImage: ImageView = holder.itemView.findViewById(R.id.post_media)
                val likeCountText: TextView = holder.itemView.findViewById(R.id.likes_count)
                val commentCountText: TextView = holder.itemView.findViewById(R.id.comments_count)
                val shareCountText: TextView = holder.itemView.findViewById(R.id.shares_count)

                // Set static values for each post
                postProfileImage.setImageResource(R.drawable.person_icon)
                postUserName.text = "User $position"
                postDate.text = "${position + 1} days ago"
                postDescription.text = "This is a sample description for post #$position"

                postImage.setImageResource(R.drawable.post_img)

                likeCountText.text = "${(position + 1) * 100} Likes"
                commentCountText.text = "${(position + 1) * 50} Comments"
                shareCountText.text = "${(position + 1) * 20} Shares"
                }

                // Return the size of the dataset (static, we assume 10 posts here)
                override fun getItemCount(): Int = 10
        }

                // Set the adapter to RecyclerView
                recyclerViewPost.adapter = adapterPost



    }
}

