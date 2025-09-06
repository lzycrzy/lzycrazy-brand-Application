package com.example.lzycrazy

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.fragment.app.DialogFragment
import android.view.ViewGroup.LayoutParams
import android.graphics.Color
import androidx.core.graphics.drawable.toDrawable

class EditProfileDialogFragment : DialogFragment() {

    private lateinit var ivProfileImage: ImageView
    private lateinit var etFirstName: EditText
    private lateinit var etLastName: EditText
    private lateinit var btnSave: Button
    private lateinit var btnCancel: Button

    private var selectedImageUri: Uri? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_edit_profile, container, false)

        ivProfileImage = view.findViewById(R.id.ivProfileImage)
        etFirstName = view.findViewById(R.id.editTextFirstName)
        etLastName = view.findViewById(R.id.editTextLastName)
        btnSave = view.findViewById(R.id.btnSave)
        btnCancel = view.findViewById(R.id.btnCancel)

        // Pick image from gallery
        ivProfileImage.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, 101)
        }

        btnCancel.setOnClickListener {
            dismiss()
        }

        btnSave.setOnClickListener {
            val firstName = etFirstName.text.toString().trim()
            val lastName = etLastName.text.toString().trim()
            val fullName = "$firstName $lastName"

            val result = Bundle().apply {
                putString("username", fullName)
                putString("imageUri", selectedImageUri?.toString())
            }

            parentFragmentManager.setFragmentResult("editProfileResult", result)
            dismiss()
        }

        return view
    }

    override fun onStart() {
        super.onStart()
        // Make dialog full-width and keep height wrap_content
        dialog?.window?.setLayout(
            LayoutParams.MATCH_PARENT,
            LayoutParams.WRAP_CONTENT
        )
        dialog?.window?.setBackgroundDrawable(Color.TRANSPARENT.toDrawable())
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 101 && resultCode == Activity.RESULT_OK) {
            selectedImageUri = data?.data
            ivProfileImage.setImageURI(selectedImageUri)
        }
    }
}
