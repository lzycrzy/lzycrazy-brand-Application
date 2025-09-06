package com.example.lzycrazy.not_in_currently_used

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lzycrazy.R
import com.google.android.material.button.MaterialButton
import de.hdodenhof.circleimageview.CircleImageView

class ConnectFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_connect, container, false)

        // Setup RecyclerView
        val recyclerView = view.findViewById<RecyclerView>(R.id.friendsRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val friends = listOf(
            Friend("Rony Coleman", R.drawable.ic_friend),
            Friend("John Doe", R.drawable.ic_friend),
            Friend("Jane Smith", R.drawable.ic_friend),
            Friend("David Lee", R.drawable.ic_friend)
        )

        recyclerView.adapter = FriendAdapter(friends)

        return view
    }

    data class Friend(val name: String, val profileImageRes: Int)

    private inner class FriendAdapter(private val friends: List<Friend>) :
        RecyclerView.Adapter<FriendViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_friend, parent, false)
            return FriendViewHolder(view)
        }

        override fun onBindViewHolder(holder: FriendViewHolder, position: Int) {
            holder.bind(friends[position])
        }

        override fun getItemCount(): Int = friends.size
    }

    private inner class FriendViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.friendName)
        private val profileImage: CircleImageView = itemView.findViewById(R.id.profileImage)
        private val followButton: MaterialButton = itemView.findViewById(R.id.followButton)
        private val menuButton: ImageButton = itemView.findViewById(R.id.menuButton)

        fun bind(friend: Friend) {
            nameTextView.text = friend.name
            profileImage.setImageResource(friend.profileImageRes)

            // Reset to default state (in case view is recycled)
            followButton.isEnabled = true
            followButton.text = "Follow"
            followButton.setTextColor(ContextCompat.getColor(requireContext(), android.R.color.white))
            followButton.setBackgroundResource(R.drawable.rounded_black_button)

            // Follow button click logic
            followButton.setOnClickListener {
                followButton.text = "Following"
                followButton.isEnabled = false
                followButton.setTextColor(ContextCompat.getColor(requireContext(), android.R.color.white))
                followButton.setBackgroundResource(R.drawable.rounded_gray_button)
            }

            // Menu button click logic
            menuButton.setOnClickListener {
                Toast.makeText(
                    requireContext(),
                    "More options for ${friend.name}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = ConnectFragment()
    }
}