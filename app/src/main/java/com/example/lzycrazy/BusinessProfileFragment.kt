package com.example.lzycrazy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment
import com.google.android.material.textfield.TextInputEditText

class BusinessProfileDialogFragment : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_business_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val nameEditText = view.findViewById<TextInputEditText>(R.id.nameEditText)
        val emailEditText = view.findViewById<TextInputEditText>(R.id.emailEditText)
        val phoneEditText = view.findViewById<TextInputEditText>(R.id.phoneEditText)
        val messageEditText = view.findViewById<TextInputEditText>(R.id.messageEditText)
        val submitButton = view.findViewById<Button>(R.id.submitButton)

        submitButton.setOnClickListener {
            if (validateForm(nameEditText, emailEditText, phoneEditText, messageEditText)) {

                dismiss()
                BusinessConfirmationDialogFragment().show(
                    requireActivity().supportFragmentManager,
                    "ConfirmationDialog"
                )
            }
        }
    }

    private fun validateForm(
        name: TextInputEditText,
        email: TextInputEditText,
        phone: TextInputEditText,
        message: TextInputEditText
    ): Boolean {
        var isValid = true

        if (name.text.toString().trim().isEmpty()) {
            name.error = "Please enter your name"
            isValid = false
        }

        if (email.text.toString().trim().isEmpty()) {
            email.error = "Please enter your email"
            isValid = false
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email.text.toString()).matches()) {
            email.error = "Please enter a valid email"
            isValid = false
        }

        if (phone.text.toString().trim().isEmpty()) {
            phone.error = "Please enter your phone number"
            isValid = false
        }

        if (message.text.toString().trim().isEmpty()) {
            message.error = "Please enter your message"
            isValid = false
        } else if (message.text.toString().length < 10) {
            message.error = "Message should be at least 10 characters"
            isValid = false
        }

        return isValid
    }
}

