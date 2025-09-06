package com.example.lzycrazy

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import com.example.lzycrazy.databinding.DialogEmailBinding
import com.google.android.material.textfield.TextInputLayout
import androidx.core.graphics.drawable.toDrawable
import com.example.lzycrazy.withoutlogin.careers.ApplicationDialogFragment

class EmailDialogFragment : DialogFragment() {

    private lateinit var binding: DialogEmailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
        dialog?.window?.setBackgroundDrawable(Color.TRANSPARENT.toDrawable())

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogEmailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnContinue.setOnClickListener {
            validateAndContinue()
        }
    }

    private fun openApplicationForm(email: String) {
        val dialog = ApplicationDialogFragment().apply {
            arguments = Bundle().apply {
                putString("email", email)
            }
        }
        dialog.show(parentFragmentManager, "application_dialog")
    }

    override fun onStart() {
        super.onStart()

        dialog?.window?.setLayout(
            (resources.displayMetrics.widthPixels * 0.9).toInt(),
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }

    private fun validateAndContinue() {
        val email = binding.etEmail.text.toString().trim()

        if (email.isEmpty()) {
            showError("Email is required")
            return
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            showError("Please enter a valid email")
            return
        }
        dismiss()
        openNextDialog(email)
    }

    private fun showError(message: String) {
        val inputLayout: TextInputLayout = binding.emailInputLayout
        inputLayout.error = message
        binding.etEmail.requestFocus()
    }

    private fun openNextDialog(email: String) {
        (activity as? DialogListener)?.onEmailSubmitted(email)
    }

    interface DialogListener {
        fun onEmailSubmitted(email: String)
    }
}
