package com.example.lzycrazy

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import java.util.*

class EditDetailsFragment : Fragment() {

    private lateinit var editTextEmail: EditText
    private lateinit var editTextPhone: EditText
    private lateinit var editTextCountry: EditText
    private lateinit var editTextState: EditText
    private lateinit var editTextCity: EditText
    private lateinit var editTextBusinessLink: EditText
    private lateinit var spinnerGender: Spinner
    private lateinit var spinnerNeighbour: Spinner
    private lateinit var etDob: EditText
    private lateinit var btnSave: Button
    private lateinit var btnCancel: ImageButton

    private val userDetailsViewModel: UserDetailsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_edit_details, container, false)

        // Initialize views
        editTextEmail = view.findViewById(R.id.editTextEmail)
        editTextPhone = view.findViewById(R.id.editTextPhone)
        editTextCountry = view.findViewById(R.id.editTextCountry)
        editTextState = view.findViewById(R.id.editTextState)
        editTextCity = view.findViewById(R.id.editTextCity)
        editTextBusinessLink = view.findViewById(R.id.editTextBusinessLink)
        spinnerGender = view.findViewById(R.id.spinnerGender)
        spinnerNeighbour = view.findViewById(R.id.spinnerNeighbour)
        etDob = view.findViewById(R.id.etDob)
        btnSave = view.findViewById(R.id.btnSave)
        btnCancel = view.findViewById(R.id.btnCancel)

        // Setup Gender spinner
        val genderOptions = listOf("Male", "Female", "Other")
        spinnerGender.adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            genderOptions
        ).apply {
            setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }

        // Setup Neighbour spinner
        val neighbourOptions = listOf("Neighbour A", "Neighbour B", "Neighbour C")
        spinnerNeighbour.adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            neighbourOptions
        ).apply {
            setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }

        // Date Picker for DOB
        etDob.setOnClickListener {
            val calendar = Calendar.getInstance()
            val datePickerDialog = DatePickerDialog(
                requireContext(),
                { _, year, month, day ->
                    etDob.setText("$day/${month + 1}/$year")
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )
            datePickerDialog.show()
        }

        // Save button → update ViewModel
        btnSave.setOnClickListener {
            userDetailsViewModel.saveDetails(
                email = editTextEmail.text.toString(),
                phone = editTextPhone.text.toString(),
                gender = spinnerGender.selectedItem.toString(),
                businessLink = editTextBusinessLink.text.toString(),
                country = editTextCountry.text.toString(),
                state = editTextState.text.toString(),
                city = editTextCity.text.toString(),
                neighbour = spinnerNeighbour.selectedItem.toString(),
                dob = etDob.text.toString()
            )
            parentFragmentManager.popBackStack()
        }

        // Cancel button → just go back
        btnCancel.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        return view
    }
}
