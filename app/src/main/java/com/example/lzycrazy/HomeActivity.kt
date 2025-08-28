package com.example.lzycrazy

//import android.content.Intent
import android.os.Bundle
//import android.util.Log
//import android.widget.Button
//import android.widget.EditText
//import android.widget.TextView
//import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
//import com.example.lzycrazy.auth.ApiClient
//import com.example.lzycrazy.auth.ForgotPasswordActivity
//import com.example.lzycrazy.auth.LoginRequest
//import com.example.lzycrazy.auth.LoginResponse
//import com.example.lzycrazy.auth.SignupActivity
import com.example.lzycrazy.chat.Chat
import com.example.lzycrazy.chat.ChatAdapter
import com.example.lzycrazy.chat.ChatsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
//import com.example.lzycrazy.withoutlogin.AboutUsActivity
//import com.example.lzycrazy.withoutlogin.NewsActivity
//import com.example.lzycrazy.withoutlogin.careers.ApplicationDialogFragment
//import com.example.lzycrazy.withoutlogin.marketplace.MarketplaceActivity
//import com.example.lzycrazy.withoutlogin.services.ServicesActivity
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // ✅ Show ChatsFragment by default
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, ChatsFragment())
            .commit()
    }
}



//class HomeActivity : AppCompatActivity(),
//    EmailDialogFragment.DialogListener,
//    ApplicationDialogFragment.DialogListener,
//    TasksDialogFragment.DialogListener {
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_home)
//
//        // Keep IDs from the first version
//        val emailEditText = findViewById<EditText>(R.id.editTextEmailAddress)
//        val passwordEditText = findViewById<EditText>(R.id.editTextPassword)
//        val loginButton = findViewById<Button>(R.id.loginButton)
//        val createAccountButton = findViewById<Button>(R.id.createAccountButton)
//        val forgotPasswordText = findViewById<TextView>(R.id.forgotPasswordText)
//        val aboutUsButton = findViewById<Button>(R.id.about_button)
//        val careersButton = findViewById<Button>(R.id.careers_button)
//        val servicesButton = findViewById<Button>(R.id.services_button)
//        val newsButton = findViewById<Button>(R.id.news_button)
//
//        // Additional button from second version
//        val marketplaceButton = findViewById<Button>(R.id.marketplace_button)
//
//        loginButton.setOnClickListener {
//            val email = emailEditText.text.toString().trim()
//            val password = passwordEditText.text.toString()
//
//            if (email.isEmpty() || password.isEmpty()) {
//                Toast.makeText(this, "Email and password are required", Toast.LENGTH_SHORT).show()
//                return@setOnClickListener
//            }
//
//            val loginRequest = LoginRequest(email, password)
//
//            ApiClient.instance.loginUser(loginRequest).enqueue(object : Callback<LoginResponse> {
//                override fun onResponse(
//                    call: Call<LoginResponse>,
//                    response: Response<LoginResponse>
//                ) {
//                    if (response.isSuccessful) {
//                        Toast.makeText(this@HomeActivity, "Login Successful", Toast.LENGTH_SHORT).show()
//                        startActivity(Intent(this@HomeActivity, HomeScreenActivity::class.java))
//                        finish()
//                    } else {
//                        Toast.makeText(this@HomeActivity, "Invalid credentials", Toast.LENGTH_SHORT).show()
//                    }
//                }
//
//                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
//                    Toast.makeText(this@HomeActivity, "Login failed: ${t.message}", Toast.LENGTH_LONG).show()
//                }
//            })
//        }
//
//        createAccountButton.setOnClickListener {
//            startActivity(Intent(this, SignupActivity::class.java))
//        }
//
//        forgotPasswordText.setOnClickListener {
//            startActivity(Intent(this, ForgotPasswordActivity::class.java))
//        }
//
//        aboutUsButton.setOnClickListener {
//            startActivity(Intent(this, AboutUsActivity::class.java))
//        }
//
//        careersButton.setOnClickListener {
//            showEmailDialogForCareers()
//        }
//
//        servicesButton.setOnClickListener {
//            startActivity(Intent(this, ServicesActivity::class.java))
//        }
//
//        newsButton.setOnClickListener {
//            startActivity(Intent(this, NewsActivity::class.java))
//        }
//
//        marketplaceButton?.setOnClickListener {
//            startActivity(Intent(this, MarketplaceActivity::class.java))
//        }
//    }
//
//    private fun showEmailDialogForCareers() {
//        val dialog = EmailDialogFragment()
//        dialog.show(supportFragmentManager, "email_dialog_careers")
//    }
//
//    override fun onEmailSubmitted(email: String) {
//        openApplicationForm(email)
//    }
//
//    private fun openApplicationForm(email: String) {
//        val dialog = ApplicationDialogFragment().apply {
//            arguments = Bundle().apply {
//                putString("email", email)
//            }
//        }
//        dialog.show(supportFragmentManager, "application_dialog_careers")
//    }
//
//    override fun onBackToLoginRequested() {
//        Toast.makeText(this, "Application cancelled.", Toast.LENGTH_SHORT).show()
//    }
//
//    override fun onApplicationSubmitted(data: Map<String, String>) {
//        Toast.makeText(this, "Application data received, proceeding to task selection.", Toast.LENGTH_LONG).show()
//        Log.d("HomeActivity", "Application Data for Careers: $data")
//        openTasksDialog()
//    }
//
//    private fun openTasksDialog() {
//        val dialog = TasksDialogFragment()
//        dialog.show(supportFragmentManager, "tasks_dialog_careers")
//    }
//
//    override fun onTasksSubmitted(selectedShift: String) {
//        Log.d("HomeActivity", "Selected Shift for Careers: $selectedShift")
//        openSuccessDialog()
//    }
//
//    private fun openSuccessDialog() {
//        val dialog = SuccessDialogFragment()
//        dialog.show(supportFragmentManager, "success_dialog_careers")
//    }
//}
