package com.example.lzycrazy.withlogin.divesh_post

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.lzycrazy.R

class FarmMilkFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_farm_milk, container, false)

        // Initialize spinners and other views from layout
        val spinnerCountry: Spinner = view.findViewById(R.id.spinnerCountry)
        val spinnerState: Spinner = view.findViewById(R.id.spinnerState)
        val spinnerCity: Spinner = view.findViewById(R.id.spinnerCity)
        val spinnerLocality: Spinner = view.findViewById(R.id.spinnerLocality)

        val txtState: View = view.findViewById(R.id.txtState)
        val txtCity: View = view.findViewById(R.id.txtCity)
        val txtLocality: View = view.findViewById(R.id.txtLocality)

        // Initialize Full Address components
        val txtFullAddress: TextView = view.findViewById(R.id.txtFullAddress)
        val edtFullAddress: EditText = view.findViewById(R.id.edtFullAddress)

        // Array for the country spinner (Choose Country, India, and Other)
        val countries = listOf("-- Choose Country --", "India", "United States", "United Kingdom", "Canada", "Australia", "Germany", "Other")
        val countryAdapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, countries)
        countryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCountry.adapter = countryAdapter

        // Country selection listener
        spinnerCountry.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedCountry = parent?.getItemAtPosition(position).toString()

                if (selectedCountry == "India") {
                    // Show State, City, and Locality spinners
                    txtState.visibility = View.VISIBLE
                    spinnerState.visibility = View.VISIBLE
                    txtCity.visibility = View.VISIBLE
                    spinnerCity.visibility = View.VISIBLE
                    txtLocality.visibility = View.VISIBLE
                    spinnerLocality.visibility = View.VISIBLE

                    // Hide Full Address fields
                    txtFullAddress.visibility = View.GONE
                    edtFullAddress.visibility = View.GONE

                    // Set default values for state and city spinners
                    val states = listOf("-- Choose State --", "State 1", "State 2", "State 3")
                    val cities = listOf("-- Choose City --", "City 1", "City 2", "City 3")
                    val localities =
                        listOf("-- Choose Locality --", "Locality 1", "Locality 2", "Locality 3")

                    // Set adapters for state, city, and locality spinners
                    val stateAdapter =
                        ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, states)
                    val cityAdapter =
                        ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, cities)
                    val localityAdapter = ArrayAdapter(
                        requireContext(),
                        android.R.layout.simple_spinner_item,
                        localities
                    )

                    stateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    localityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

                    spinnerState.adapter = stateAdapter
                    spinnerCity.adapter = cityAdapter
                    spinnerLocality.adapter = localityAdapter

                } else if (selectedCountry == "Other") {
                    // Show Full Address fields
                    txtState.visibility = View.GONE
                    spinnerState.visibility = View.GONE
                    txtCity.visibility = View.GONE
                    spinnerCity.visibility = View.GONE
                    txtLocality.visibility = View.GONE
                    spinnerLocality.visibility = View.GONE

                    // Show Full Address
                    txtFullAddress.visibility = View.VISIBLE
                    edtFullAddress.visibility = View.VISIBLE
                } else {
                    // Hide all spinners and address fields if "Choose Country" is selected
                    txtState.visibility = View.GONE
                    spinnerState.visibility = View.GONE
                    txtCity.visibility = View.GONE
                    spinnerCity.visibility = View.GONE
                    txtLocality.visibility = View.GONE
                    spinnerLocality.visibility = View.GONE

                    txtFullAddress.visibility = View.GONE
                    edtFullAddress.visibility = View.GONE
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Optionally handle when nothing is selected
            }
        }
        return view
    }

}
