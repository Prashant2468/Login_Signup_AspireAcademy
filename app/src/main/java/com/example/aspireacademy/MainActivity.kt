package com.example.aspireacademy

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        var btn_login=findViewById<Button>(R.id.btn_login)
        var btn_signup=findViewById<Button>(R.id.btn_signup)

        btn_login.setOnClickListener {
            var intent=Intent(this,LoginPage::class.java);
            startActivity(intent)
        }
        btn_signup.setOnClickListener {
            var intent=Intent(this,SignupPage::class.java);
            startActivity(intent)

        }

    }
}