package com.example.lzycrazy.not_in_currently_used

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.lzycrazy.R

class AboutFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_about, container, false)

        // Find Edit Details button
        val btnEditDetails: Button = view.findViewById(R.id.btnEditDetails)

        // On click → Open FragmentEditDetailsScreen
//        btnEditDetails.setOnClickListener {
//            parentFragmentManager.beginTransaction()
//                .replace(R.id.fragment_container, EditDetailsFragment()) // Make sure your container id is correct
//                .addToBackStack(null)
//                .commit()
//        }

        return view
    }
}
