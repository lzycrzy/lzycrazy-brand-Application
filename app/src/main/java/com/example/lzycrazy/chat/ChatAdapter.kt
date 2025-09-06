package com.example.lzycrazy.chat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.lzycrazy.R

class ChatAdapter(
    private var chatList: MutableList<Chat>,   // ✅ changed to var (so we can update it)
    private val onItemClick: (Chat) -> Unit    // ✅ click listener added
) : RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {

    inner class ChatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgProfile: ImageView = itemView.findViewById(R.id.imgProfile)
        val tvName: TextView = itemView.findViewById(R.id.tvName)
        val tvMessage: TextView = itemView.findViewById(R.id.tvMessage)
        val tvTime: TextView = itemView.findViewById(R.id.tvTime)
        val tvBadge: TextView = itemView.findViewById(R.id.tvBadge)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.chat_item, parent, false)
        return ChatViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        val chat = chatList[position]
        holder.tvName.text = chat.name
        holder.tvMessage.text = chat.message
        holder.tvTime.text = chat.time

        if (chat.unreadCount > 0) {
            // Unread case
            holder.tvBadge.text = chat.unreadCount.toString()
            holder.tvBadge.visibility = View.VISIBLE
            holder.tvTime.setTextColor(
                ContextCompat.getColor(holder.itemView.context, R.color.chat_unread)
            )
        } else {
            // Seen case
            holder.tvBadge.visibility = View.GONE
            holder.tvTime.setTextColor(
                ContextCompat.getColor(holder.itemView.context, R.color.chat_seen)
            )
        }

        // ✅ Handle click
        holder.itemView.setOnClickListener {
            onItemClick(chat)
        }
    }

    override fun getItemCount(): Int = chatList.size

    // ✅ Support Swipe to Delete
    fun removeAt(position: Int) {
        chatList.removeAt(position)
        notifyItemRemoved(position)
    }

    // ✅ Support Search (update list)
    fun updateList(newList: MutableList<Chat>) {
        chatList = newList
        notifyDataSetChanged()
    }
}
