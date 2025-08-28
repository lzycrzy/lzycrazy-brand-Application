package com.example.lzycrazy  // change if your package is different

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.lzycrazy.R

class TopNavigationFragment : Fragment() {

    override fun onCreateView(
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
    }

    private fun showProfilePopup(anchorView: View) {
        val popupView = LayoutInflater.from(requireContext())
            .inflate(R.layout.layout_profile_popup, null)

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
}
