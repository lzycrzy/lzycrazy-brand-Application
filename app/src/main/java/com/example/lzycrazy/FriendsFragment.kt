package com.example.lzycrazy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FriendsFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: FriendAdapter
    private val friendList = listOf(
        Friend("Zeeshu Ali", "58 mutual friends, including Zeeshan Shareef and Moin Kassar", "Lives in Delhi, India"),
        Friend("Zeeshu Ali", "58 mutual friends, including Zeeshan Shareef and Moin Kassar", "Lives in Delhi, India"),
        Friend("Zeeshu Ali", "58 mutual friends, including Zeeshan Shareef and Moin Kassar", "Lives in Delhi, India"),
        Friend("Zeeshu Ali", "58 mutual friends, including Zeeshan Shareef and Moin Kassar", "Lives in Delhi, India")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_friends, container, false)
        recyclerView = view.findViewById(R.id.friendsRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = FriendAdapter(friendList)
        recyclerView.adapter = adapter
        return view
    }
}
