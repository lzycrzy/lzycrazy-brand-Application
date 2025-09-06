package com.example.lzycrazy

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class HiringActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hiring)
        val jobCategorySpinner: Spinner = findViewById(R.id.spinnerJobCategory)
        val experienceLevelSpinner: Spinner = findViewById(R.id.spinnerExperienceLevel)

        val buttonSingUp = findViewById<Button>(R.id.buttonSignUp)
        buttonSingUp.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }

        val jobCategories = arrayOf("Marketing", "Sales", "Development", "Operations")
        val experienceLevels = arrayOf("Fresher", "1–2 Years", "3–5 Years", "5+ Years")

        jobCategorySpinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, jobCategories)
        experienceLevelSpinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, experienceLevels)

    }
}