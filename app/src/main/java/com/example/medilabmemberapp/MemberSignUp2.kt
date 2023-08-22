package com.example.medilabmemberapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import com.example.medilabmemberapp.helpers.PrefsHelper

class MemberSignUp2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_member_sign_up2)

        //find phoneand email in edittext
        val editPhone = findViewById<EditText>(R.id.editPhone)
        val editEmail = findViewById<EditText>(R.id.editEmail)

        val email = editEmail.text
        val phone = editPhone.text


        //get data from radio(radiomale "male
        //find the two radio buttons
        val radioMale = findViewById<RadioButton>(R.id.radioFemale)
        val radioFemale = findViewById<RadioButton>(R.id.radioMale)




        val register2 = findViewById<Button>(R.id.register2)
        register2.setOnClickListener {

            PrefsHelper.savePrefs(applicationContext, "phone",phone.toString())
            PrefsHelper.savePrefs(applicationContext, "email",email.toString())



            var gender = "N/A"
            if (radioMale.isChecked) {
                gender = "male"
                PrefsHelper.savePrefs(applicationContext, "gender",gender)
            }
            if (radioFemale.isChecked){
                gender = "female"
                PrefsHelper.savePrefs(applicationContext, "gender",gender)

            }



            val intent = Intent(applicationContext,MemberSignUp3::class.java)
            startActivity(intent)
        }
    }
}