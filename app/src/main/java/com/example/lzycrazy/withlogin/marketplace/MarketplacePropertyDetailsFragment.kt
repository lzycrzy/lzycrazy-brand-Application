package com.example.lzycrazy.withlogin.marketplace


import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.example.lzycrazy.R

class MarketplacePropertyDetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_marketplace_property_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Map image click handler
        val mapImage = view.findViewById<ImageView>(R.id.mapImage)
        mapImage.setOnClickListener {
            val gmmIntentUri = Uri.parse("geo:28.523445,77.221012?q=The Duke Of Wellington")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")

            // Check if Google Maps is installed
            if (mapIntent.resolveActivity(requireActivity().packageManager) != null) {
                startActivity(mapIntent)
            } else {
                Toast.makeText(requireContext(), "Google Maps app not found", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        // Show the sign-in dialog
        val postNowButton = view.findViewById<Button>(R.id.postNowButton)
        postNowButton.setOnClickListener {
            showSignInDialog()
        }

    }

    private fun showSignInDialog() {
        val dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.dialog_marketplace_login_first)
        dialog.setCancelable(true)
        val closeButton: ImageView = dialog.findViewById(R.id.close_button)
        closeButton.setOnClickListener {
            dialog.dismiss()
        }
        val loginButton: Button = dialog.findViewById(R.id.login_button)
        loginButton.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }
}