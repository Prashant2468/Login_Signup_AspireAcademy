package com.example.aspireacademy

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class LoginPage : AppCompatActivity() {
    private  lateinit var LL_Error: LinearLayout
    var i=1
    var f=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_login_page)

        val edEnroll = findViewById<TextInputEditText>(R.id.ed_enroll)
        val edPassword = findViewById<TextInputEditText>(R.id.ed_password)
        val txtErrorEnroll = findViewById<TextView>(R.id.txt_error_enroll)
        val txtErrorPassword = findViewById<TextView>(R.id.txt_error_password)
        val txtJumpOnSignup = findViewById<TextView>(R.id.txt_jumpon_signup)
        val btnLogin = findViewById<Button>(R.id.btn_login)
        LL_Error=findViewById(R.id.LL_Error)

        btnLogin.setOnClickListener {
            val enrollNo = edEnroll.text.toString()
            val password = edPassword.text.toString()

            // Enrollment validation
            if (enrollNo.length != 15) {
                txtErrorEnroll.apply {
                    text = "Enrollment No must be 15 digits."
                    showError()
                    visibility = View.VISIBLE
                }
            } else if (enrollNo != "202404104610014") {
                txtErrorEnroll.apply {
                    text = "Enter valid Enrollment Number!"
                    visibility = View.VISIBLE
                    showError()
                }
            } else {
                txtErrorEnroll.visibility = View.INVISIBLE
            }

            // Password validation
            if (password.isEmpty()) {
                txtErrorPassword.apply {
                    text = "Password should not be empty!"
                    visibility = View.VISIBLE
                    showError()
                }
            } else if (password != "12345") {
                txtErrorPassword.apply {
                    text = "Invalid Password!"
                    visibility = View.VISIBLE
                }
            } else {
                txtErrorPassword.visibility = View.INVISIBLE
            }

            // If both validations are successful
            if (enrollNo.length == 15 && enrollNo == "202404104610014" && password == "12345") {
                Toast.makeText(this, "Login Successful", Toast.LENGTH_LONG).show()
            }
        }

        txtJumpOnSignup.setOnClickListener {
            startActivity(Intent(this, SignupPage::class.java))
        }


    }
    fun showError() {

        if (i == 1) {
            Toast.makeText(this,"Ok",Toast.LENGTH_SHORT).show()

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
