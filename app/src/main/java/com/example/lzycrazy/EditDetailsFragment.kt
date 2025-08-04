package com.example.lzycrazy

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment

class EditDetailsFragment : Fragment(R.layout.fragment_edit_details) {

    private lateinit var spinnerGender: Spinner
    private lateinit var spinnerCountry: Spinner
    private lateinit var spinnerStatus: Spinner
    private lateinit var spinnerBlood: Spinner

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Bind spinner views from XML
        spinnerGender = view.findViewById(R.id.spinnerGender)
        spinnerCountry = view.findViewById(R.id.spinnerCountry)
        spinnerStatus = view.findViewById(R.id.spinnerStatus)
        spinnerBlood = view.findViewById(R.id.spinnerBlood)

        setupSpinners()
    }

    private fun setupSpinners() {
        setSpinner(spinnerGender, listOf("Select Gender", "Male", "Female", "Other"))
        setSpinner(spinnerCountry, listOf("Select Country", "India", "USA", "UK"))
        setSpinner(spinnerStatus, listOf("Select Marital Status", "Single", "Married", "Divorced"))
        setSpinner(spinnerBlood, listOf("Select Blood Group", "A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-"))
    }

    private fun setSpinner(spinner: Spinner, items: List<String>) {
        val adapter = object : ArrayAdapter<String>(
            requireContext(),
            android.R.layout.simple_spinner_item,
            items
        ) {
            override fun isEnabled(position: Int): Boolean {
                return position != 0 // Disable hint item
            }

            override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
                val view = super.getDropDownView(position, convertView, parent)
                val tv = view as TextView
                tv.setTextColor(if (position == 0) Color.GRAY else Color.BLACK)
                return view
            }
        }

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
    }
}
