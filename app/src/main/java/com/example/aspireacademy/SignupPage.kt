package com.example.aspireacademy

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.LinearLayout
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText

class SignupPage : AppCompatActivity() {

    private lateinit var fullNameField: TextInputEditText
    private lateinit var dobField: TextInputEditText
    private lateinit var phoneField: TextInputEditText
    private lateinit var emailField: TextInputEditText
    private lateinit var genderRadioGroup: RadioGroup
    private lateinit var courseSpinner: Spinner
    private lateinit var signupButton: Button
    private  lateinit var LL_Error:LinearLayout
    var i=1
    var f=0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_signup_page)

        // Initialize views
        fullNameField = findViewById(R.id.ed_fullname)
        dobField = findViewById(R.id.ed_dob)
        phoneField = findViewById(R.id.ed_phone)
        emailField = findViewById(R.id.ed_email)
        genderRadioGroup = findViewById(R.id.RGgender)
        courseSpinner = findViewById(R.id.spinner_course)
        signupButton = findViewById(R.id.btn_signup)
        LL_Error=findViewById(R.id.LL_Error)
        var txt_jumpon_login=findViewById<TextView>(R.id.txt_jumpon_login)

        val courses = arrayOf("Select a Course", "MCA", "MCOM", "MBA")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, courses)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        courseSpinner.adapter = adapter

        txt_jumpon_login.setOnClickListener {
            var intent= Intent(this,LoginPage::class.java)
            startActivity(intent)
        }

        signupButton.setOnClickListener {
            if (validateInputs()) {
                Toast.makeText(this, "Signup Successful!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validateInputs(): Boolean {
        // Full Name Validation
        if (fullNameField.text.isNullOrEmpty()) {
            fullNameField.error = "Full Name is required"
            showError()
            return false
        }

        // Date of Birth Validation
        if (dobField.text.isNullOrEmpty()) {
            dobField.error = "Date of Birth is required"
            showError()
            return false
        } else if (!isValidDate(dobField.text.toString())) {
            dobField.error = "Invalid Date Format (DD/MM/YYYY)"
            showError()
            return false
        }

        // Gender Validation
        if (genderRadioGroup.checkedRadioButtonId == -1) {
            Toast.makeText(this, "Please select your gender", Toast.LENGTH_SHORT).show()
            showError()
            return false
        }

        // Phone Number Validation
        if (phoneField.text.isNullOrEmpty()) {
            phoneField.error = "Phone Number is required"
            showError()
            return false
        } else if (phoneField.text.toString().length != 10) {
            phoneField.error = "Phone Number must be 10 digits"
            showError()
            return false
        }

        // Email Validation
        if (emailField.text.isNullOrEmpty()) {
            emailField.error = "Email is required"
            showError()
            return false
        } else if (!isValidEmail(emailField.text.toString())) {
            emailField.error = "Invalid Email Address"
            showError()
            return false
        }

        // Course Validation
        if (courseSpinner.selectedItemPosition == 0) {
            Toast.makeText(this, "Please select a course", Toast.LENGTH_SHORT).show()
            showError()
            return false
        }

        return true
    }
    private fun isValidDate(date: String): Boolean {
        // Regex for date validation (DD/MM/YYYY)
        val regex = Regex("^\\d{2}/\\d{2}/\\d{4}$")
        return regex.matches(date)
    }

    private fun isValidEmail(email: String): Boolean {
        // Regex for email validation
        val regex = Regex("^[A-Za-z0-9+_.-]+@(.+)$")
        return regex.matches(email)
    }
    fun showError() {

        if (i == 1) {

            LL_Error.visibility = View.VISIBLE
            LL_Error.translationY = -250f
            LL_Error.alpha = f.toFloat()
            LL_Error.animate().translationY(0f).alpha(1f).duration = 700
            i = 0
            val handler = Handler(Looper.getMainLooper())
            handler.postDelayed({

                if (i == 0) {
                    LL_Error.translationY = 0f
                    LL_Error.alpha = 1f
                    LL_Error.animate().translationY(-250f).alpha(0f).duration = 700
                    i = 1
                }
            }, 3000)
        }
    }

}