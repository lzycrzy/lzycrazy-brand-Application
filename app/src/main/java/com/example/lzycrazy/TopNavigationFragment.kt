package com.example.lzycrazy

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

class TopNavigationFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_top_navigation, container, false)

        val profileImageView = view.findViewById<ImageView>(R.id.profile_image)

        profileImageView.setOnClickListener {
            val popupView = LayoutInflater.from(requireContext())
                .inflate(R.layout.layout_profile_popup, null)

            val popupUserName = popupView.findViewById<TextView>(R.id.tvName)
            val popupUserEmail = popupView.findViewById<TextView>(R.id.tvEmail)
            val popupUserId = popupView.findViewById<TextView>(R.id.tvId)
            val signOutButton = popupView.findViewById<Button>(R.id.signUpButton)

            // Set user details (replace with real user data)
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

            // Show below the profile image (anchored position)
            popupWindow.showAsDropDown(profileImageView, -50, 20)

            signOutButton.setOnClickListener {
                popupWindow.dismiss()
                Toast.makeText(requireContext(), "Sign out", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }
}