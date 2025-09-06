package com.example.lzycrazy

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.android.material.button.MaterialButton

class AboutFragment : Fragment() {

    // Profile + navigation
    private lateinit var profileImage: ImageView
    private lateinit var userName: TextView
    private lateinit var btnEditProfile: Button
    private lateinit var btnEditDetails: Button
    private lateinit var btnSettings: ImageButton
    private lateinit var btnMyAds: MaterialButton

    // User details fields
    private lateinit var textEmail: TextView
    private lateinit var textPhone: TextView
    private lateinit var textGender: TextView
    private lateinit var textBusinessLink: TextView
    private lateinit var textCountry: TextView
    private lateinit var textState: TextView
    private lateinit var textCity: TextView
    private lateinit var textNeighbour: TextView
    private lateinit var textDob: TextView

    // Shared ViewModel
    private val userDetailsViewModel: UserDetailsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_about, container, false)

        // Initialize Profile + buttons
        profileImage = view.findViewById(R.id.profileImage)
        userName = view.findViewById(R.id.userName)
        btnEditProfile = view.findViewById(R.id.btn_edit_profile)
        btnEditDetails = view.findViewById(R.id.btnEditDetails)
        btnSettings = view.findViewById(R.id.btn_settings)
        btnMyAds = view.findViewById(R.id.btnMyAds)

        // Initialize details fields
        textEmail = view.findViewById(R.id.textEmail)
        textPhone = view.findViewById(R.id.textPhone)
        textGender = view.findViewById(R.id.textGender)
        textBusinessLink = view.findViewById(R.id.textBusinessLink)
        textCountry = view.findViewById(R.id.textCountry)
        textState = view.findViewById(R.id.textState)
        textCity = view.findViewById(R.id.textCity)
        textNeighbour = view.findViewById(R.id.textNeighbour)
        textDob = view.findViewById(R.id.textDob)

        // Open EditProfileDialogFragment
        btnEditProfile.setOnClickListener {
            val dialog = EditProfileDialogFragment()
            dialog.show(parentFragmentManager, "EditProfileDialog")
        }

        // Open EditDetailsFragment
        btnEditDetails.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, EditDetailsFragment())
                .addToBackStack(null)
                .commit()
        }

        // Open Settings (MenuFragment)
        btnSettings.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, MenuFragment())
                .addToBackStack(null)
                .commit()
        }

        // Open Dashboard (My Ads)
        btnMyAds.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, DashboardFragment())
                .addToBackStack(null)
                .commit()
        }

        // Listen for result from EditProfileDialogFragment (name + profile pic)
        parentFragmentManager.setFragmentResultListener("editProfileResult", this) { _, bundle ->
            val newName = bundle.getString("username")
            val newImageUri = bundle.getString("imageUri")
            newName?.let { userName.text = it }
            newImageUri?.let { profileImage.setImageURI(it.toUri()) }
        }

        // Observe ViewModel for details
        observeData()

        return view
    }

    private fun observeData() {
        userDetailsViewModel.email.observe(viewLifecycleOwner) {
            textEmail.text = "Email: $it"
        }
        userDetailsViewModel.phone.observe(viewLifecycleOwner) {
            textPhone.text = "Phone: $it"
        }
        userDetailsViewModel.gender.observe(viewLifecycleOwner) {
            textGender.text = "Gender: $it"
        }
        userDetailsViewModel.businessLink.observe(viewLifecycleOwner) { link ->
            textBusinessLink.text = "Business Profile Link: $link"

            // Make link clickable
            textBusinessLink.setOnClickListener {
                if (!link.isNullOrEmpty()) {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
                    startActivity(intent)
                }
            }
        }
        userDetailsViewModel.country.observe(viewLifecycleOwner) {
            textCountry.text = "Country: $it"
        }
        userDetailsViewModel.state.observe(viewLifecycleOwner) {
            textState.text = "State: $it"
        }
        userDetailsViewModel.city.observe(viewLifecycleOwner) {
            textCity.text = "City: $it"
        }
        userDetailsViewModel.neighbour.observe(viewLifecycleOwner) {
            textNeighbour.text = "Neighbour: $it"
        }
        userDetailsViewModel.dob.observe(viewLifecycleOwner) {
            textDob.text = "Date of Birth: $it"
        }
    }
}
