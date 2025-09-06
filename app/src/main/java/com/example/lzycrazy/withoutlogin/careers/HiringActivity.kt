package com.example.lzycrazy.withoutlogin.careers

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.example.lzycrazy.R
import com.example.lzycrazy.auth.SignupActivity

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

        jobCategorySpinner.adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, jobCategories)
        experienceLevelSpinner.adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, experienceLevels)

    }
}