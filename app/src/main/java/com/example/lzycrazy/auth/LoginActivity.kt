package com.example.lzycrazy.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.lzycrazy.HomeScreenActivity
import com.example.lzycrazy.auth.LoginRequest
import com.example.lzycrazy.auth.LoginResponse
import com.example.lzycrazy.R
import com.example.lzycrazy.auth.SignupActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val emailEditText = findViewById<EditText>(R.id.editTextEmailAddress)
        val passwordEditText = findViewById<EditText>(R.id.editTextPassword)
        val loginButton = findViewById<Button>(R.id.loginButton)
        val createAccountButton = findViewById<Button>(R.id.createAccountButton)
        val forgotPasswordText = findViewById<TextView>(R.id.forgotPasswordText)

        loginButton.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Email and password are required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val loginRequest = LoginRequest(email, password)

            ApiClient.instance.loginUser(loginRequest).enqueue(object : Callback<LoginResponse> {
                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                    if (response.isSuccessful) {
                        val token = response.body()?.token
                        Toast.makeText(this@LoginActivity, "Login Successful", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@LoginActivity, HomeScreenActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(this@LoginActivity, "Invalid credentials", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Toast.makeText(this@LoginActivity, "Error: ${t.message}", Toast.LENGTH_LONG).show()
                }
            })
        }

        createAccountButton.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }

        // Add this for Forgot Password navigation
        forgotPasswordText.setOnClickListener {
            startActivity(Intent(this, ForgotPasswordActivity::class.java))
        }
    }
}