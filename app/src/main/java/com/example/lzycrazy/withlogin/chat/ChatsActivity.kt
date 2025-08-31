package com.example.lzycrazy.withlogin.chat

import android.content.Intent
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lzycrazy.R


class ChatsActivity : AppCompatActivity() {

    private lateinit var adapter: ChatAdapter
    private lateinit var chatList: MutableList<Chat>
    private lateinit var searchBar: EditText
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chats) // Ensure you have activity_chats.xml

        // Initialize views
        recyclerView = findViewById(R.id.chat_recycler_view)
        searchBar = findViewById(R.id.search_bar)

        // Setup RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Initialize sample data
        chatList = MutableList(10) { index ->
            Chat(
                name = "Item $index",
                message = "Last message for item $index",
                time = "3:${index.toString().padStart(2, '0')} pm", // Example: 3:00 pm, 3:01 pm
                unreadCount = if (index % 2 == 0) index else 0
            )
        }

        // Adapter setup
        adapter = ChatAdapter(chatList) { chat ->
            val intent = Intent(this, ChatDetailActivity::class.java) // Use 'this' for Activity context
            intent.putExtra("chatName", chat.name)
            startActivity(intent)
        }
        recyclerView.adapter = adapter

        // Search functionality
        searchBar.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable?) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val query = s.toString().trim().lowercase()
                val filteredList = if (query.isEmpty()) {
                    chatList // Show original list if query is empty
                } else {
                    // Create a new list for filtering to avoid modifying original during iteration
                    ArrayList(chatList).filter { it.name.lowercase().contains(query) }.toMutableList()
                }
                adapter.updateList(filteredList)
            }
        })

        // Swipe to delete/archive setup
        val itemTouchHelperCallback = object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean = false // Not interested in drag-and-drop

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.bindingAdapterPosition // Use bindingAdapterPosition for safety
                if (position != RecyclerView.NO_POSITION) { // Check for valid position
                    adapter.removeAt(position) // This should remove from the list shown by adapter

                    if (direction == ItemTouchHelper.RIGHT) {
                        Toast.makeText(this@ChatsActivity, "Chat Archived", Toast.LENGTH_SHORT).show()
                    } else if (direction == ItemTouchHelper.LEFT) {
                        Toast.makeText(this@ChatsActivity, "Chat Deleted", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun onChildDraw(
                c: Canvas,
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean
            ) {
                val itemView = viewHolder.itemView
                val paint = Paint()

                // Calculate icon size and margin dynamically (example values)
                val iconHeight = 48 // Desired icon height in pixels
                val iconWidth = 48  // Desired icon width in pixels
                val iconMarginVertical = (itemView.height - iconHeight) / 2
                val iconMarginHorizontal = 40 // Horizontal margin from the edge of the item

                if (dX > 0) { // Swiping RIGHT → Archive
                    paint.color = Color.parseColor("#2196F3") // Blue background
                    c.drawRect(
                        itemView.left.toFloat(), itemView.top.toFloat(),
                        itemView.left + dX, itemView.bottom.toFloat(), paint
                    )

                    val icon = ContextCompat.getDrawable(this@ChatsActivity, R.drawable.ic_archive_chats)
                    icon?.let {
                        val iconTop = itemView.top + iconMarginVertical
                        val iconBottom = iconTop + iconHeight
                        val iconLeft = itemView.left + iconMarginHorizontal
                        val iconRight = iconLeft + iconWidth
                        it.setBounds(iconLeft, iconTop, iconRight, iconBottom)
                        it.draw(c)
                    }
                } else if (dX < 0) { // Swiping LEFT → Delete
                    paint.color = Color.parseColor("#F44336") // Red background
                    c.drawRect(
                        itemView.right + dX, itemView.top.toFloat(),
                        itemView.right.toFloat(), itemView.bottom.toFloat(), paint
                    )

                    val icon = ContextCompat.getDrawable(this@ChatsActivity, R.drawable.ic_delete)
                    icon?.let {
                        val iconTop = itemView.top + iconMarginVertical
                        val iconBottom = iconTop + iconHeight
                        val iconRight = itemView.right - iconMarginHorizontal
                        val iconLeft = iconRight - iconWidth
                        it.setBounds(iconLeft, iconTop, iconRight, iconBottom)
                        it.draw(c)
                    }
                }

                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
            }
        }
        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }
}
