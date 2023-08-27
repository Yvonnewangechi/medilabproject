package com.example.medilabmemberapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class Screen2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_screen2)

        val next = findViewById<TextView>(R.id.next1)
        next.setOnClickListener {
            //Intent message to screen2
            val intent = Intent(applicationContext,BottomSheet::class.java)
            startActivity(intent)

        }

        val skip = findViewById<TextView>(R.id.skip)
        skip.setOnClickListener {
            val intent = Intent(applicationContext,BottomSheet::class.java)
            startActivity(intent)
        }
    }
}