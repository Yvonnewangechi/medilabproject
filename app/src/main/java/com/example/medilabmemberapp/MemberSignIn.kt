package com.example.medilabmemberapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MemberSignIn : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_member_sign_in)

        val login = findViewById<Button>(R.id.login)
        login.setOnClickListener {



            Toast.makeText(applicationContext, "Login successful", Toast.LENGTH_SHORT).show()
        }
    }
}