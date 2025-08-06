package com.example.lzycrazy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import de.hdodenhof.circleimageview.CircleImageView
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class HomeFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var recyclerViewStories: RecyclerView
    private lateinit var storiesAdapter: RecyclerView.Adapter<*>
    private lateinit var recyclerViewPost: RecyclerView
    private lateinit var postsAdapter: RecyclerView.Adapter<*>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // --- Using the recycler view for the Profile or status of the different persons ---
        recyclerViewStories = view.findViewById(R.id.rvStories) // rvStories is in fragment_home.xml

        // Set the layout manager
        recyclerViewStories.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        // Create a simple adapter for stories
        storiesAdapter = object : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
            override fun onCreateViewHolder(
                parent: ViewGroup,
                viewType: Int
            ): RecyclerView.ViewHolder {
                // Use requireContext() for LayoutInflater in Fragments for safety
                val itemView = LayoutInflater.from(requireContext())
                    .inflate(R.layout.item_story, parent, false)
                return object : RecyclerView.ViewHolder(itemView) {}
            }

            override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
                val storyImage: ImageView = holder.itemView.findViewById(R.id.storyImage)
                val userName: TextView = holder.itemView.findViewById(R.id.userName)

                storyImage.setImageResource(R.drawable.story_person_icon)
                userName.text = "User $position"
            }

            override fun getItemCount(): Int = 10
        }
        recyclerViewStories.adapter = storiesAdapter

        // --- Using the recycler view for displaying posts ---
        recyclerViewPost = view.findViewById(R.id.rvPost) // rvPost is in fragment_home.xml

        // Set the layout manager
        recyclerViewPost.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        // Create a simple adapter for posts
        postsAdapter = object : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
            override fun onCreateViewHolder(
                parent: ViewGroup,
                viewType: Int
            ): RecyclerView.ViewHolder {
                val itemView = LayoutInflater.from(requireContext())
                    .inflate(R.layout.item_post, parent, false)
                return object : RecyclerView.ViewHolder(itemView) {}
            }

            override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
                val postProfileImage: CircleImageView =
                    holder.itemView.findViewById(R.id.profile_image)
                val postUserName: TextView = holder.itemView.findViewById(R.id.user_name)
                val postDate: TextView = holder.itemView.findViewById(R.id.post_date)
                val postDescription: TextView = holder.itemView.findViewById(R.id.post_description)
                val postImage: ImageView = holder.itemView.findViewById(R.id.post_media)
                val likeCountText: TextView = holder.itemView.findViewById(R.id.likes_count)
                val commentCountText: TextView = holder.itemView.findViewById(R.id.comments_count)
                val shareCountText: TextView = holder.itemView.findViewById(R.id.shares_count)

                postProfileImage.setImageResource(R.drawable.person_icon)
                postUserName.text = "User $position"
                postDate.text = "${position + 1} days ago"
                postDescription.text = "This is a sample description for post #$position"
                postImage.setImageResource(R.drawable.post_img)
                likeCountText.text = "${(position + 1) * 100} Likes"
                commentCountText.text = "${(position + 1) * 50} Comments"
                shareCountText.text = "${(position + 1) * 20} Shares"
            }

            override fun getItemCount(): Int = 10
        }
        recyclerViewPost.adapter = postsAdapter
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}