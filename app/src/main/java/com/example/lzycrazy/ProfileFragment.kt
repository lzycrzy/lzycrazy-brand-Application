package com.example.lzycrazy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        // Find About button
        val btnAbout: Button = view.findViewById(R.id.btnAbout)

        // Navigate to AboutFragment when clicked
        btnAbout.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, AboutFragment()) // use your Activity's container id
                .addToBackStack(null)
                .commit()
        }

        return view
    }
}
