package com.example.lzycrazy.chat

import android.content.Intent
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lzycrazy.R

class ChatsFragment : Fragment() {

    private lateinit var adapter: ChatAdapter
    private lateinit var chatList: MutableList<Chat>
    private lateinit var searchBar: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_chats, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.chat_recycler_view)
        searchBar = view.findViewById(R.id.search_bar)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        chatList = MutableList(10) { index ->
            Chat(
                name = "Item $index",
                message = "Last message for item $index",
                time = "3:${index}0 pm",
                unreadCount = if (index % 2 == 0) index else 0
            )
        }

        // ✅ Adapter setup
        adapter = ChatAdapter(chatList) { chat ->
            val intent = Intent(requireContext(), ChatDetailActivity::class.java)
            intent.putExtra("chatName", chat.name)
            startActivity(intent)
        }

        recyclerView.adapter = adapter

        // ✅ Search functionality (filter by chat name only)
        searchBar.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable?) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val query = s.toString().trim().lowercase()
                val filteredList = if (query.isEmpty()) {
                    chatList
                } else {
                    chatList.filter { it.name.lowercase().contains(query) }.toMutableList()
                }
                adapter.updateList(filteredList)
            }
        })

        // ✅ Swipe to delete/archive with background + icons + Toast
        val itemTouchHelper = ItemTouchHelper(object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean = false

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                adapter.removeAt(position)

                if (direction == ItemTouchHelper.RIGHT) {
                    Toast.makeText(requireContext(), "Chat Archived", Toast.LENGTH_SHORT).show()
                } else if (direction == ItemTouchHelper.LEFT) {
                    Toast.makeText(requireContext(), "Chat Deleted", Toast.LENGTH_SHORT).show()
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
                val iconMargin = (itemView.height - 48) / 2 // Adjust icon placement

                if (dX > 0) {
                    // Swiping RIGHT → Archive
                    paint.color = Color.parseColor("#2196F3") // Blue
                    c.drawRect(
                        itemView.left.toFloat(), itemView.top.toFloat(),
                        itemView.left + dX, itemView.bottom.toFloat(), paint
                    )

                    val icon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_archive_chats)
                    icon?.let {
                        val iconTop = itemView.top + (itemView.height - it.intrinsicHeight) / 2
                        val iconLeft = itemView.left + iconMargin
                        val iconRight = iconLeft + it.intrinsicWidth
                        val iconBottom = iconTop + it.intrinsicHeight
                        it.setBounds(iconLeft, iconTop, iconRight, iconBottom)
                        it.draw(c)
                    }
                } else if (dX < 0) {
                    // Swiping LEFT → Delete
                    paint.color = Color.parseColor("#F44336") // Red
                    c.drawRect(
                        itemView.right + dX, itemView.top.toFloat(),
                        itemView.right.toFloat(), itemView.bottom.toFloat(), paint
                    )

                    val icon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_delete)
                    icon?.let {
                        val iconTop = itemView.top + (itemView.height - it.intrinsicHeight) / 2
                        val iconRight = itemView.right - iconMargin
                        val iconLeft = iconRight - it.intrinsicWidth
                        val iconBottom = iconTop + it.intrinsicHeight
                        it.setBounds(iconLeft, iconTop, iconRight, iconBottom)
                        it.draw(c)
                    }
                }

                super.onChildDraw(
                    c,
                    recyclerView,
                    viewHolder,
                    dX,
                    dY,
                    actionState,
                    isCurrentlyActive
                )
            }
        })
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }
}
