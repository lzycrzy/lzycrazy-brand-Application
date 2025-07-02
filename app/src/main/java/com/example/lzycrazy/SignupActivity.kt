package com.example.lzycrazy

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.hbb20.CountryCodePicker
import retrofit2.Call

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

        val signUpButton = findViewById<Button>(R.id.signUpButton) // Add this ID to your XML Button
        signUpButton.setOnClickListener {
            val name = findViewById<EditText>(R.id.editTextFullName).text.toString()
            val email = findViewById<EditText>(R.id.editTextEmailAddress).text.toString()
            val phone = ccp.fullNumberWithPlus
            val password = findViewById<EditText>(R.id.editTextPassword).text.toString()

            val registerRequest = RegisterRequest(
                fullName = name,
                email = email,
                phone = phone,
                password = password
            )

            ApiClient.instance.registerUser(registerRequest)
                .enqueue(object : retrofit2.Callback<Void> {
                    override fun onResponse(call: Call<Void>, response: retrofit2.Response<Void>) {
                        if (response.isSuccessful) {
                            Toast.makeText(this@SignupActivity, "Registered Successfully", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(this@SignupActivity, "Registration Failed", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<Void>, t: Throwable) {
                        Toast.makeText(this@SignupActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                    }
                })
        }



        // Example: Getting full phone number on form submission (e.g., register button click)
        // val registerButton = findViewById<Button>(R.id.registerButton)
        // registerButton.setOnClickListener {
        //     val fullPhoneNumber = ccp.fullNumberWithPlus
        //     Toast.makeText(this, "Phone: $fullPhoneNumber", Toast.LENGTH_SHORT).show()
        // }
    }
}
