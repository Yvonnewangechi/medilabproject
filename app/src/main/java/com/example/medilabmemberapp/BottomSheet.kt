package com.example.medilabmemberapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.FrameLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior

class BottomSheet : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_sheet)

        val bottomSheet:FrameLayout = findViewById(R.id.bottomSheet)
        BottomSheetBehavior.from(bottomSheet).apply {
            peekHeight = 250
            this.state = BottomSheetBehavior.STATE_COLLAPSED
        }

        //button to login
        val buttonLogin : Button = findViewById(R.id.buttonLoginButton)
        buttonLogin.setOnClickListener {
            val intent = Intent(applicationContext,MemberSignIn::class.java)
            startActivity(intent)
        }

        val buttonRegister : Button = findViewById(R.id.bottomRegisterButton)
        buttonRegister.setOnClickListener {
            val intent = Intent(applicationContext,MemberSignUp::class.java)
            startActivity(intent)
        }
        //add intent to HomeActivity
        val buttonCheckLabs :Button = findViewById(R.id.btnHome)
        buttonCheckLabs.setOnClickListener {
            val intent = Intent(applicationContext,HomeActivity::class.java)
            startActivity(intent)

        }


    }
}