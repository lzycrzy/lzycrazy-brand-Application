package com.example.lzycrazy

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val emailEditText = findViewById<EditText>(R.id.editTextEmailAddress)
        val passwordEditText = findViewById<EditText>(R.id.editTextPassword)
        val loginButton = findViewById<Button>(R.id.loginButton)
        val createAccountButton = findViewById<Button>(R.id.createAccountButton)
        val forgotPasswordText = findViewById<TextView>(R.id.forgotPasswordText)
        val aboutUsButton = findViewById<Button>(R.id.aboutUsButton)
        val careersButton = findViewById<Button>(R.id.careersButton)
        val servicesButton = findViewById<Button>(R.id.servicesButton)

        val newsButton = findViewById<Button>(R.id.newsButton)

        loginButton.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Email and password are required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val loginRequest = LoginRequest(email, password)

            ApiClient.instance.loginUser(loginRequest).enqueue(object : Callback<LoginResponse> {
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    if (response.isSuccessful) {
                        Toast.makeText(this@HomeActivity, "Login Successful", Toast.LENGTH_SHORT)
                            .show()
                        startActivity(Intent(this@HomeActivity, HomeScreenActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(this@HomeActivity, "Invalid credentials", Toast.LENGTH_SHORT)
                            .show()
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Toast.makeText(this@HomeActivity, "Login failed: ${t.message}", Toast.LENGTH_LONG)
                        .show()
                }
            })
        }

        createAccountButton.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }

        forgotPasswordText.setOnClickListener {
            startActivity(Intent(this, ForgotPasswordActivity::class.java))
        }

        aboutUsButton.setOnClickListener {
            startActivity(Intent(this, AboutUsActivity::class.java))
        }

        careersButton.setOnClickListener {
            startActivity(Intent(this, HiringActivity::class.java))
        }

        servicesButton.setOnClickListener {
            startActivity(Intent(this, ServicesActivity::class.java))
        }


        newsButton.setOnClickListener {
            startActivity(Intent(this, NewsActivity::class.java))
        }
    }
}
