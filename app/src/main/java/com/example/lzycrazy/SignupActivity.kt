package com.example.lzycrazy

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.lzycrazy.withoutlogin.NewsActivity
import com.example.lzycrazy.withoutlogin.marketplace.MarketplaceActivity
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

        val signUpButton = findViewById<Button>(R.id.signUpButton)
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

        // 🔹 Extra Buttons with Correct IDs
        val aboutUsButton = findViewById<Button>(R.id.aboutUsButton)
        val careersButton = findViewById<Button>(R.id.careersButton)
        val servicesButton = findViewById<Button>(R.id.servicesButton)
        val newsButton = findViewById<Button>(R.id.newsButton)
        val marketplaceButton = findViewById<Button>(R.id.marketplaceButton)

        aboutUsButton.setOnClickListener {
            Log.d("SIGNUP", "About Us button clicked")
            startActivity(Intent(this, AboutUsActivity::class.java))
        }

        careersButton.setOnClickListener {
            Log.d("SIGNUP", "Careers button clicked")
            showEmailDialogForCareers()
        }

        servicesButton.setOnClickListener {
            Log.d("SIGNUP", "Services button clicked")
            startActivity(Intent(this, ServicesActivity::class.java))
        }

        newsButton.setOnClickListener {
            Log.d("SIGNUP", "News button clicked")
            startActivity(Intent(this, NewsActivity::class.java))
        }

        marketplaceButton.setOnClickListener {
            Log.d("SIGNUP", "Marketplace button clicked")
            startActivity(Intent(this, MarketplaceActivity::class.java))
        }

    }

    // 🔹 Careers dialog placeholder
    private fun showEmailDialogForCareers() {
        Toast.makeText(this, "Careers dialog coming soon...", Toast.LENGTH_SHORT).show()
    }
}
