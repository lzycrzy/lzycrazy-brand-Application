package com.example.lzycrazy.withoutlogin.careers

import android.R
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.DialogFragment
import com.example.lzycrazy.databinding.DialogApplicationBinding

class ApplicationDialogFragment : DialogFragment() {

    private lateinit var binding: DialogApplicationBinding
    private var videoUri: Uri? = null
    private val experienceLevels = arrayOf("Fresher", "1-2 Years", "3-5 Years", "5+ Years")
    private val jobCategories = arrayOf("Marketing", "Sales", "Development", "Operations")

    private val videoResultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            videoUri = result.data?.data
            Toast.makeText(context, "Video recorded successfully", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogApplicationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val email = arguments?.getString("email") ?: ""
        binding.tvWelcome.text = "Welcome, $email Please complete your hiring form."

        setupSpinners()
        setupWordCount()
        setupListeners()
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.let { window ->
            val width = (resources.displayMetrics.widthPixels * 0.90).toInt()
            val height = ViewGroup.LayoutParams.WRAP_CONTENT
            window.setLayout(width, height)
        }
    }

    private fun setupSpinners() {
        val experienceAdapter = ArrayAdapter(
            requireContext(),
            R.layout.simple_dropdown_item_1line,
            experienceLevels
        )
        binding.actvExperience.setAdapter(experienceAdapter)

        val jobCategoryAdapter = ArrayAdapter(
            requireContext(),
            R.layout.simple_dropdown_item_1line,
            jobCategories
        )
        binding.actvJobCategory.setAdapter(jobCategoryAdapter)
    }

    private fun setupWordCount() {
        binding.etIntroduction.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                val text = s.toString()
                val wordCount = if (text.isBlank()) 0 else text.split("\\s+".toRegex()).size
                binding.tvWordCount.text = "Word count: $wordCount/50"
            }
        })
    }

    private fun setupListeners() {
        binding.tvBackToLogin.setOnClickListener {
            dismiss()
            (activity as? DialogListener)?.onBackToLoginRequested()
        }

        binding.btnRecordVideo.setOnClickListener {
            recordVideo()
        }

        binding.btnSubmitApplication.setOnClickListener {
            if (validateForm()) {
                submitApplication()
            }
        }
    }

    private fun recordVideo() {
        val intent = Intent(MediaStore.ACTION_VIDEO_CAPTURE).apply {
            putExtra(MediaStore.EXTRA_DURATION_LIMIT, 15)
            putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1)
        }
        videoResultLauncher.launch(intent)
    }

    private fun validateForm(): Boolean {
        var isValid = true

        if (binding.etCountry.text.toString().isBlank()) {
            binding.etCountry.error = "Country is required"
            isValid = false
        }

        if (binding.etState.text.toString().isBlank()) {
            binding.etState.error = "State is required"
            isValid = false
        }

        if (binding.etCity.text.toString().isBlank()) {
            binding.etCity.error = "City is required"
            isValid = false
        }

        if (binding.etEducation.text.toString().isBlank()) {
            binding.etEducation.error = "Education is required"
            isValid = false
        }

        if (binding.actvExperience.text.toString().isBlank()) {
            binding.actvExperience.error = "Experience level is required"
            isValid = false
        }

        if (binding.actvJobCategory.text.toString().isBlank()) {
            binding.actvJobCategory.error = "Job category is required"
            isValid = false
        }

        val introText = binding.etIntroduction.text.toString()
        if (introText.isBlank()) {
            binding.etIntroduction.error = "Introduction is required"
            isValid = false
        } else {
            val wordCount = introText.split("\\s+".toRegex()).size
            if (wordCount > 50) {
                binding.etIntroduction.error = "Maximum 50 words allowed"
                isValid = false
            }
        }

        if (videoUri == null) {
            Toast.makeText(context, "Please record a video introduction", Toast.LENGTH_SHORT).show()
            isValid = false
        }

        return isValid
    }

    private fun submitApplication() {
        val applicationData = mapOf(
            "email" to (arguments?.getString("email") ?: ""),
            "country" to binding.etCountry.text.toString(),
            "state" to binding.etState.text.toString(),
            "city" to binding.etCity.text.toString(),
            "education" to binding.etEducation.text.toString(),
            "experience" to binding.actvExperience.text.toString(),
            "jobCategory" to binding.actvJobCategory.text.toString(),
            "introduction" to binding.etIntroduction.text.toString(),
            "videoUri" to videoUri.toString()
        )
        dismiss()
        (activity as? DialogListener)?.onApplicationSubmitted(applicationData)
    }

    interface DialogListener {
        fun onBackToLoginRequested()
        fun onApplicationSubmitted(data: Map<String, String>)
    }
}