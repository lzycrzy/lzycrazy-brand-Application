package com.example.lzycrazy

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FriendAdapter(private val friendList: List<Friend>) :
    RecyclerView.Adapter<FriendAdapter.FriendViewHolder>() {

    inner class FriendViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tvName)
        val tvMutual: TextView = itemView.findViewById(R.id.tvMutual)
        val tvLocation: TextView = itemView.findViewById(R.id.tvLocation)
        val btnFriends: Button = itemView.findViewById(R.id.btnFriends)
        val btnMessage: Button = itemView.findViewById(R.id.btnMessage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_friend_card, parent, false)
        return FriendViewHolder(view)
    }

    override fun onBindViewHolder(holder: FriendViewHolder, position: Int) {
        val friend = friendList[position]
        holder.tvName.text = friend.name
        holder.tvMutual.text = friend.mutualFriends
        holder.tvLocation.text = friend.location
        // Handle clicks if needed
    }

    override fun getItemCount(): Int = friendList.size
}
