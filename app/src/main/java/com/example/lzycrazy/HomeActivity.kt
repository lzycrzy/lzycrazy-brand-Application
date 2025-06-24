package com.example.lzycrazy

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val loginButton = findViewById<Button>(R.id.loginButton)
        val signupButton = findViewById<Button>(R.id.signupButton)
        val aboutUsButton = findViewById<Button>(R.id.aboutUsButton)
        val hiringButton = findViewById<Button>(R.id.hiringButton)
        val servicesButton = findViewById<Button>(R.id.servicesButton)


        loginButton.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        signupButton.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }
        aboutUsButton.setOnClickListener {
            startActivity(Intent(this, AboutUsActivity::class.java))
        }
        hiringButton.setOnClickListener {
            startActivity(Intent(this, HiringActivity::class.java))
        }
        servicesButton.setOnClickListener {
            startActivity(Intent(this, ServicesActivity::class.java))
        }

    }
}
