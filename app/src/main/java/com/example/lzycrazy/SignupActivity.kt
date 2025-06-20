package com.example.lzycrazy

import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.hbb20.CountryCodePicker

class SignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val alreadyAccountText = findViewById<TextView>(R.id.alreadyHaveAccountText)
        val ccp = findViewById<CountryCodePicker>(R.id.ccp)
        val phoneEditText = findViewById<EditText>(R.id.editTextPhone)

        // Link the EditText with CountryCodePicker
        ccp.registerCarrierNumberEditText(phoneEditText)

        // When submitting the form
        val fullPhoneNumber = ccp.fullNumberWithPlus
        Log.d("SIGNUP", "User Phone: $fullPhoneNumber")

        alreadyAccountText.setOnClickListener {
            finish() // Go back to Login screen
        }


        // Example: Getting full phone number on form submission (e.g., register button click)
        // val registerButton = findViewById<Button>(R.id.registerButton)
        // registerButton.setOnClickListener {
        //     val fullPhoneNumber = ccp.fullNumberWithPlus
        //     Toast.makeText(this, "Phone: $fullPhoneNumber", Toast.LENGTH_SHORT).show()
        // }
    }
}
