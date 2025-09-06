package com.example.lzycrazy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomeFragment : Fragment() {

    private lateinit var recyclerViewStories: RecyclerView
    private lateinit var recyclerViewPost: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for the fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize RecyclerViews
        recyclerViewStories = view.findViewById(R.id.storiesRecyclerView)
        recyclerViewStories.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerViewStories.adapter = StoryAdapter()

        recyclerViewPost = view.findViewById(R.id.rvPost)
        recyclerViewPost.layoutManager = LinearLayoutManager(requireContext())
        recyclerViewPost.adapter = PostAdapter()
    }

    // Adapter for Stories RecyclerView
    class StoryAdapter : RecyclerView.Adapter<StoryAdapter.StoryViewHolder>() {

        // Inflating the layout for each story item
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryViewHolder {
            val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_home_screen_story, parent, false)
            return StoryViewHolder(itemView)
        }

        // Binding data to each view holder
        override fun onBindViewHolder(holder: StoryViewHolder, position: Int) {
            // Example data for story
            holder.storyImage.setImageResource(R.drawable.ic_home_screen_avatar) // Set your story image
            holder.userName.text = "User $position" // Example user name
            holder.storyText.text = "This is story #$position" // Example story text
        }

        override fun getItemCount(): Int = 10 // Number of items in the stories list

        // ViewHolder class for the story item
        inner class StoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val storyImage: ImageView = itemView.findViewById(R.id.storyContent)
            val userName: TextView = itemView.findViewById(R.id.storyText)
            val storyText: TextView = itemView.findViewById(R.id.storyText)
        }
    }

    // Adapter for Posts RecyclerView
    class PostAdapter : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
            val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_post, parent, false)
            return PostViewHolder(itemView)
        }

        override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
            // Bind post data here (for simplicity, using static data)
            holder.postDescription.text = "Post description for post #$position"
            holder.likesCount.text = "2,344 Likes"
            holder.commentsCount.text = "555 Comments"
            holder.sharesCount.text = "175 Shares"
        }

        override fun getItemCount(): Int = 10 // Number of posts in the list

        // ViewHolder class for the post item
        inner class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val postDescription: TextView = itemView.findViewById(R.id.post_description)
            val likesCount: TextView = itemView.findViewById(R.id.likes_count)
            val commentsCount: TextView = itemView.findViewById(R.id.comments_count)
            val sharesCount: TextView = itemView.findViewById(R.id.shares_count)
        }
    }
}
