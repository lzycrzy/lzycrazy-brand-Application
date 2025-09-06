package com.example.lzycrazy

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.PopupWindow
import android.widget.TextView
import android.widget.Toast
import androidx.core.graphics.drawable.toDrawable
import androidx.fragment.app.Fragment
import com.example.lzycrazy.withlogin.chat.ChatsActivity
import com.example.lzycrazy.withlogin.notifications.NotificationActivity

class TopNavigationFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_top_navigation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bellIcon = view.findViewById<ImageView>(R.id.bellIcon)
        bellIcon.setOnClickListener {
            val intent = Intent(context, NotificationActivity::class.java)
            startActivity(intent)
        }

        val messageIcon = view.findViewById<ImageView>(R.id.messageIcon)
        messageIcon.setOnClickListener {
            val intent = Intent(context, ChatsActivity::class.java)
            startActivity(intent)
        }

        val profileContainer = view.findViewById<FrameLayout>(R.id.profileContainer)
        profileContainer.setOnClickListener {
            showProfilePopup(it)
        }
    }

    private fun showProfilePopup(anchorView: View) {
        val popupView = layoutInflater.inflate(R.layout.layout_profile_popup, null)

        val popupWindow = PopupWindow(
            popupView,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            true
        )

        popupWindow.setBackgroundDrawable(Color.TRANSPARENT.toDrawable())
        popupWindow.isOutsideTouchable = true
        popupWindow.elevation = 10f

        // Fill in user info (static for now)
        popupView.findViewById<TextView>(R.id.tvName).text = "Name"
        popupView.findViewById<TextView>(R.id.tvId).text = "ID: Lc1"
        popupView.findViewById<TextView>(R.id.tvEmail).text = "lzycrazy@gmail.com"

        // Sign out action
        popupView.findViewById<TextView>(R.id.btnSignOut).setOnClickListener {
            popupWindow.dismiss()
            Toast.makeText(requireContext(), "Signed out!", Toast.LENGTH_SHORT).show()
        }

        popupWindow.showAsDropDown(anchorView, -50, 20)
    }
}
