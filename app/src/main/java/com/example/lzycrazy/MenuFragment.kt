package com.example.lzycrazy

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment

class MenuFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Example: handle click on "Saved"
        view.findViewById<View>(R.id.saved_layout)?.setOnClickListener {
            Toast.makeText(requireContext(), "Saved clicked", Toast.LENGTH_SHORT).show()
        }

        // Example: handle click on "Logout"
        view.findViewById<View>(R.id.logout_layout)?.setOnClickListener {
            Toast.makeText(requireContext(), "Logged out", Toast.LENGTH_SHORT).show()
            // You can navigate to LoginActivity or clear session here
            // Clear back stack and go back to HomeActivity (login screen)
            val intent = Intent(requireContext(), HomeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }
}
