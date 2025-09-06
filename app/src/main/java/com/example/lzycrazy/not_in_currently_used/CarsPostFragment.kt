package com.example.lzycrazy.not_in_currently_used

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.lzycrazy.R

class CarsPostFragment : Fragment(R.layout.fragment_cars_post) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val brandSpinner = view.findViewById<Spinner>(R.id.brandSpinner)

        val brands = listOf("Brand", "Maruti", "Hyundai", "Honda", "Toyota", "Tata")

        val adapter = object : ArrayAdapter<String>(
            requireContext(),
            android.R.layout.simple_spinner_item,
            brands
        ) {
            override fun isEnabled(position: Int): Boolean {
                return position != 0 // disable "Brand" option
            }

            override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
                val view = super.getDropDownView(position, convertView, parent)
                val tv = view as TextView
                tv.setTextColor(if (position == 0) Color.GRAY else Color.BLACK)
                return view
            }
        }

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        brandSpinner.adapter = adapter
        brandSpinner.setSelection(0)
    }
}