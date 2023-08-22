package com.example.medilabmemberapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.medilabmemberapp.helpers.PrefsHelper

class MemberSignUp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_member_sign_up)

        //get data from the three edit texts
        val editSurname = findViewById<EditText>(R.id.editSurname)
        val editOthers = findViewById<EditText>(R.id.editOthers)
        val editPassword = findViewById<EditText>(R.id.editPassword)


        //get data
        val surname = editSurname.text
        val others = editOthers.text
        val password = editPassword.text


        //find register 1 button and proceed to member signup2

        val register1 = findViewById<Button>(R.id.register1)
        register1.setOnClickListener {

            //save surname
            PrefsHelper.savePrefs(applicationContext, "surname",surname.toString())
            PrefsHelper.savePrefs(applicationContext, "others",others.toString())
            PrefsHelper.savePrefs(applicationContext, "password",password.toString())

            //save surname,others,password
            val intent = Intent(applicationContext,MemberSignUp2::class.java)
            startActivity(intent)
        }
    }
}
// to create icons we use vector assets
