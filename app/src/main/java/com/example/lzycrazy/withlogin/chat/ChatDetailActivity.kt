package com.example.lzycrazy.withlogin.chat

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lzycrazy.R
import com.example.lzycrazy.withlogin.message.Message
import com.example.lzycrazy.withlogin.message.MessageAdapter
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ChatDetailActivity : AppCompatActivity() {

    private lateinit var messageAdapter: MessageAdapter
    private val messages = mutableListOf<Message>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_detail)

        val chatName = intent.getStringExtra("chatName")

        val tvChatTitle = findViewById<TextView>(R.id.tvChatTitle)
        tvChatTitle.text = chatName

        val btnBack = findViewById<ImageView>(R.id.btnBack)
        btnBack.setOnClickListener {
            finish() // closes ChatDetailActivity and goes back to ChatsFragment
        }

        val btnClose = findViewById<ImageView>(R.id.btnClose)
        btnClose.setOnClickListener {
            finish() // closes ChatDetailActivity and goes back to ChatsFragment
        }

        // ✅ RecyclerView for messages
        val recyclerChat = findViewById<RecyclerView>(R.id.recyclerChat)
        recyclerChat.layoutManager = LinearLayoutManager(this)

        // ✅ Setup adapter
        messageAdapter = MessageAdapter(messages)
        recyclerChat.adapter = messageAdapter

        // ✅ Send button
        val etMessage = findViewById<EditText>(R.id.etMessage)
        val btnSend = findViewById<Button>(R.id.btnSend)
        btnSend.setOnClickListener {
            // your send logic here
        }

        val btnMoreInfo = findViewById<ImageView>(R.id.btnMoreInfo)

        btnMoreInfo.setOnClickListener {
            val popup = PopupMenu(this, btnMoreInfo)
            popup.menuInflater.inflate(R.menu.menu_chat_options, popup.menu)

            popup.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.action_delete_user -> {
                        // Handle chat clear click
                        Toast.makeText(this, "Chat Cleared!", Toast.LENGTH_SHORT).show()
                        true
                    }
                    else -> false
                }
            }
            popup.show()
        }


        btnSend.setOnClickListener {
            val text = etMessage.text.toString().trim()
            if (text.isNotEmpty()) {
                val currentTime = SimpleDateFormat("hh:mm a", Locale.getDefault())
                    .format(Date())

                // Add "sent" message
                messages.add(Message(text, true, currentTime))
                messageAdapter.notifyItemInserted(messages.size - 1)
                recyclerChat.scrollToPosition(messages.size - 1)

                etMessage.text.clear()

                // Simulate "received" reply after sending
                recyclerChat.postDelayed({
                    val replyTime = SimpleDateFormat("hh:mm a", Locale.getDefault())
                        .format(Date())

                    messages.add(Message("Reply to: $text", false, replyTime))
                    messageAdapter.notifyItemInserted(messages.size - 1)
                    recyclerChat.scrollToPosition(messages.size - 1)
                }, 1000)
            }
        }

    }
}
