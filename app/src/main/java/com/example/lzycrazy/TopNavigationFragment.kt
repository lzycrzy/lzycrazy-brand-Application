package com.example.lzycrazy  // change if your package is different

<<<<<<< HEAD
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
=======
import android.content.Intent
import android.graphics.Color
>>>>>>> 3818ee7e7ac955f3076add2364d7e5ed85f9236b
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
<<<<<<< HEAD
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.lzycrazy.R
=======
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.PopupWindow
import android.widget.TextView
import android.widget.Toast
import androidx.core.graphics.drawable.toDrawable
import androidx.fragment.app.Fragment
import com.example.lzycrazy.withlogin.chat.ChatsActivity
import com.example.lzycrazy.withlogin.notifications.NotificationActivity
>>>>>>> 3818ee7e7ac955f3076add2364d7e5ed85f9236b

class TopNavigationFragment : Fragment() {

    override fun onCreateView(
<<<<<<< HEAD
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_top_navigation, container, false)

        val profileImageView = view.findViewById<ImageView>(R.id.profile_image)

        // Make sure it's clickable
        profileImageView.isClickable = true
        profileImageView.isFocusable = true

        profileImageView.setOnClickListener {
            showProfilePopup(it)
        }

        return view
=======
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_top_navigation, container, false)
>>>>>>> 3818ee7e7ac955f3076add2364d7e5ed85f9236b
    }

    private fun showProfilePopup(anchorView: View) {
        val popupView = LayoutInflater.from(requireContext())
            .inflate(R.layout.layout_profile_popup, null)

<<<<<<< HEAD
        val popupUserName = popupView.findViewById<TextView>(R.id.popupUserName)
        val popupUserEmail = popupView.findViewById<TextView>(R.id.popupUserEmail)
        val popupUserId = popupView.findViewById<TextView>(R.id.popupUserId)
        val signOutButton = popupView.findViewById<Button>(R.id.signOutButton)

        // Temporary static data — replace with actual user info
        popupUserName.text = "Name"
        popupUserId.text = "ID: Lc1"
        popupUserEmail.text = "lzycrazy@example.com"

        val popupWindow = PopupWindow(
            popupView,
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT,
            true
        )

        popupWindow.elevation = 10f
        popupWindow.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        popupWindow.isOutsideTouchable = true

        // Show popup anchored to the profile image
        popupWindow.showAsDropDown(anchorView, -50, 20)

        // Handle sign out click
        signOutButton.setOnClickListener {
            popupWindow.dismiss()
            Toast.makeText(requireContext(), "Sign out", Toast.LENGTH_SHORT).show()
        }
    }
=======
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
>>>>>>> 3818ee7e7ac955f3076add2364d7e5ed85f9236b
}
