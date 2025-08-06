package com.example.lzycrazy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class AboutFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var editButton: Button
    private lateinit var adapter: AboutAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_about, container, false)

        recyclerView = view.findViewById(R.id.aboutRecyclerView)
        editButton = view.findViewById(R.id.btnEditDetails)

        val items = listOf(
            AboutItem(R.drawable.ic_user, "Bio", "Travel enthusiast and food lover"),
            AboutItem(R.drawable.icons8_mail_96, "Email", "zeeshanali11261126@gmail.com"),
            AboutItem(R.drawable.ic_phone, "Phone", "+91 98765 43210"),
            AboutItem(R.drawable.ic_gender, "Gender", "Male"),
            AboutItem(R.drawable.ic_country, "Country", "India"),
            AboutItem(R.drawable.ic_relationship, "Relationship Status", "Single"),
            AboutItem(R.drawable.ic_website, "Website / Portfolio", "www.zeeshanportfolio.com"),
            AboutItem(R.drawable.ic_blood, "Blood Group", "B+"),
            AboutItem(R.drawable.ic_hobby, "Hobbies / Interests", "UI/UX Design, Photography, Music"),
            AboutItem(R.drawable.ic_location1, "Location / City", "Kashipur, Uttarakhand"),
            AboutItem(R.drawable.ic_dob, "Date of Birth", "15 March 2001"),
            AboutItem(R.drawable.ic_work, "Profession / Occupation", "UI/UX Designer & Web Developer")
        )

        adapter = AboutAdapter(items)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

        // Set up the edit button once
        editButton.setOnClickListener {
            findNavController().navigate(R.id.action_aboutFragment_to_editDetailsFragment)
        }

        EditProfileDialogFragment().show(parentFragmentManager, "editProfile")


        return view
    }
}
