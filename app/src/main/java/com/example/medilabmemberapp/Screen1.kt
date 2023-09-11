package com.example.medilabmemberapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class Screen1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_screen1)

        val next = findViewById<TextView>(R.id.next1)
        next.setOnClickListener {
            //Intent message to screen2
            val intent = Intent(applicationContext,Screen2::class.java)
            startActivity(intent)
        }


        val skip = findViewById<TextView>(R.id.skip)
        skip.setOnClickListener {

            val intent = Intent(applicationContext,LabTestActivity::class.java)
            startActivity(intent)
        }
    }
}