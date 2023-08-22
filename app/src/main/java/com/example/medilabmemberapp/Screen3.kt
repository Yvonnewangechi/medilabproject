package com.example.medilabmemberapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class Screen3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_screen3)

        val next = findViewById<TextView>(R.id.next1)
        next.setOnClickListener {
            //Intent message to screen3
            val intent = Intent(applicationContext,MainActivity::class.java)
            startActivity(intent)
        }



    }
}