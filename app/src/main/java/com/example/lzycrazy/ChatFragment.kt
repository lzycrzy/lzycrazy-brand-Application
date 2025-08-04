package com.example.lzycrazy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.lzycrazy.databinding.FragmentChatBinding
import com.example.lzycrazy.databinding.ItemFriendListBinding

class ChatFragment : Fragment() {

    private var _binding: FragmentChatBinding? = null
    private val binding get() = _binding!!

    private lateinit var friendsAdapter: FriendsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChatBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        binding.friendsListRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        val friendsList = listOf(
            Friend(
                name = "Sumit Kumar",
                status = "You're now friends with Aman 1y",
                timestamp = "9:41",
                imageUrl = "https://randomuser.me/api/portraits/men/1.jpg"
            ),
            Friend(
                name = "Aman Sharma",
                status = "You're now friends with Raj 8m",
                timestamp = "Yesterday",
                imageUrl = "https://randomuser.me/api/portraits/men/2.jpg"
            ),
            Friend(
                name = "Neha Patel",
                status = "You're now friends with Priya 2y",
                timestamp = "2d",
                imageUrl = "https://randomuser.me/api/portraits/women/1.jpg"
            ), Friend(
                name = "Sumit Kumar",
                status = "You're now friends with Aman 1y",
                timestamp = "9:41",
                imageUrl = "https://randomuser.me/api/portraits/men/1.jpg"
            ),
            Friend(
                name = "Aman Sharma",
                status = "You're now friends with Raj 8m",
                timestamp = "Yesterday",
                imageUrl = "https://randomuser.me/api/portraits/men/2.jpg"
            ),
            Friend(
                name = "Neha Patel",
                status = "You're now friends with Priya 2y",
                timestamp = "2d",
                imageUrl = "https://randomuser.me/api/portraits/women/1.jpg"
            ), Friend(
                name = "Sumit Kumar",
                status = "You're now friends with Aman 1y",
                timestamp = "9:41",
                imageUrl = "https://randomuser.me/api/portraits/men/1.jpg"
            ),
            Friend(
                name = "Aman Sharma",
                status = "You're now friends with Raj 8m",
                timestamp = "Yesterday",
                imageUrl = "https://randomuser.me/api/portraits/men/2.jpg"
            ),
            Friend(
                name = "Neha Patel",
                status = "You're now friends with Priya 2y",
                timestamp = "2d",
                imageUrl = "https://randomuser.me/api/portraits/women/1.jpg"
            )
        )
        friendsAdapter = FriendsAdapter(friendsList)
        binding.friendsListRecyclerView.adapter = friendsAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ChatFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}

data class Friend(
    val name: String,
    val status: String,
    val timestamp: String,
    val imageUrl: String
)

class FriendsAdapter(private val friendsList: List<Friend>) :
    RecyclerView.Adapter<FriendsAdapter.FriendViewHolder>() {

    inner class FriendViewHolder(private val binding: ItemFriendListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(friend: Friend) {
            binding.friendName.text = friend.name
            binding.friendStatus.text = friend.status
            binding.timestamp.text = friend.timestamp

            Glide.with(binding.root.context)
                .load(friend.imageUrl)
                .placeholder(R.drawable.ic_dummy_friend)
                .error(R.drawable.ic_dummy_friend)
                .into(binding.avatar)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendViewHolder {
        val binding =
            ItemFriendListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FriendViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FriendViewHolder, position: Int) {
        holder.bind(friendsList[position])
    }

    override fun getItemCount() = friendsList.size
}
