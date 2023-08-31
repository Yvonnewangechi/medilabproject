package com.example.medilabmemberapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.medilabmemberapp.constants.Constants
import com.example.medilabmemberapp.helpers.ApiHelper
import com.example.medilabmemberapp.helpers.DialogHelper
import com.example.medilabmemberapp.helpers.PrefsHelper
import org.json.JSONArray
import org.json.JSONObject

class MemberSignIn : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_member_sign_in)

        val link = findViewById<TextView>(R.id.linkregister)
        link.setOnClickListener {
            startActivity(Intent(this,MemberSignUp::class.java))
        }


        //get login button and setlistener

        val surname :EditText = findViewById(R.id.editSurname)
        val password:EditText = findViewById(R.id.editPassword)



        val login = findViewById<Button>(R.id.login)
        login.setOnClickListener {


            //specify endpoint
            val api:String  = Constants.BASE_URL +"/member_signin"
            //body payload = JSONObject
            val body = JSONObject()
            body.put("surname",surname.text.toString())
            body.put("password",password.text.toString())

            val helper = ApiHelper(applicationContext)
            helper.post(api, body, object:ApiHelper.CallBack{
                override fun onSuccess(result: JSONArray?) {
                    TODO("Not yet implemented")
                }

                override fun onSuccess(result: JSONObject?) {
                    //Logged in successfully or invalid credentials
                    if(result!!.has("refresh_token")){
                        //save access_token,refresh_token,member to the preference

                        val refresh_token = result.getString("refresh_token")
                        val acess_token = result.getString("access_token")
                        val message = result.getString("member")
                      PrefsHelper.savePrefs(applicationContext,"refresh_token",refresh_token)
                        PrefsHelper.savePrefs(applicationContext,"access_token",acess_token)

                        //get members data from the message JSONObject
                        val member = JSONObject(message)
                        val member_id = member.getString("member_id")
                        val email = member.getString("email")
                        val surname = member.getString("surname")
                        val phone = member.getString("phone")

                        PrefsHelper.savePrefs(applicationContext,"member_id",member_id)
                        PrefsHelper.savePrefs(applicationContext,"email",email)
                        PrefsHelper.savePrefs(applicationContext,"surname",surname)
                        PrefsHelper.savePrefs(applicationContext,"phone",phone)


                        val intent = Intent(applicationContext,HomeActivity::class.java)
                        startActivity(intent)


                    }//login success
                    else{
                        Toast.makeText(applicationContext, "${result.toString()}", Toast.LENGTH_SHORT).show()
                    }


                    }

                override fun onFailure(result: String?) {
                    Toast.makeText(applicationContext, "${result.toString()}", Toast.LENGTH_SHORT).show()

                }
            })

        }



    }
}