package com.example.lzycrazy

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash)

        // Post a delayed task to the main thread's message queue with a 2-second delay
        Handler(Looper.getMainLooper()).postDelayed({
            // Create an Intent to launch HomeActivity
            val intent = Intent(this, HomeActivity::class.java)

            // Start HomeActivity
            startActivity(intent)

            // Finish the current activity so it's removed from the back stack
            finish()
        }, 2000)

    }
}