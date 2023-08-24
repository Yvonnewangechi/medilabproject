package com.example.medilabmemberapp

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import com.example.medilabmemberapp.constants.Constants
import com.example.medilabmemberapp.helpers.ApiHelper
import com.example.medilabmemberapp.helpers.PrefsHelper
import org.json.JSONArray
import org.json.JSONObject
import java.util.Calendar

class MemberSignUp3 : AppCompatActivity() {
    private lateinit var buttonDatePicker: Button
    private lateinit var editTextDatePicker: EditText
    private lateinit var spinner: Spinner
    private lateinit var locationSelected:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_member_sign_up3)

        //find the DatePicker button and show date picker dialog

        buttonDatePicker = findViewById(R.id.buttonDatePicker)
        editTextDatePicker = findViewById(R.id.editTextDate)


        //assign on click listener button
        buttonDatePicker.setOnClickListener {
            showDatePickerDialog()

        }
        //find the spinner and assign it to variable

        spinner = findViewById(R.id.spinner)
        locationSelected = findViewById(R.id.locationSelected)

        val locations = listOf(1,2,3,4,5)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, locations)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
        //checking the data item that is selected and saving it on a variable



        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
               val selectedItem = parent.getItemAtPosition(position).toString()
                PrefsHelper.savePrefs(applicationContext, "location_id",selectedItem)
                Toast.makeText(applicationContext, "you selected $selectedItem", Toast.LENGTH_SHORT).show()

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                Toast.makeText(applicationContext, "Nothing selected", Toast.LENGTH_SHORT).show()
            }

        }

        
        val register3 = findViewById<Button>(R.id.register3)
        register3.setOnClickListener {

            //get all the member details from prefs and submit to the API)

            //get all the member details from prefs and submit to the API
            val surname = PrefsHelper.getPrefs(applicationContext,"surname")
            val others = PrefsHelper.getPrefs(applicationContext,"others")
            val gender = PrefsHelper.getPrefs(applicationContext,"gender")
            val email = PrefsHelper.getPrefs(applicationContext,"email")
            val phone = PrefsHelper.getPrefs(applicationContext,"phone")
            val dob = PrefsHelper.getPrefs(applicationContext,"dob")
            val password = PrefsHelper.getPrefs(applicationContext,"password")
            val location_id = PrefsHelper.getPrefs(applicationContext,"location_id")

            //api->string
            //data(body)->JSONObject
            //callBack interface->JSONObject
            val api = Constants.BASE_URL +"/member_signup"
            val body = JSONObject()
            body.put("surname",surname.toString())
            body.put("others",others.toString())
            body.put("gender",gender.toString())
            body.put("email",email.toString())
            body.put("phone",phone.toString())
            body.put("dob",dob.toString())
            body.put("password",password.toString())
            body.put("location_id",location_id.toString())

            //create an object from api helper class
            val helper = ApiHelper(applicationContext)












            Toast.makeText(applicationContext, "Captured All details", Toast.LENGTH_SHORT).show()
            val intent = Intent(applicationContext,MemberSignIn::class.java)
            startActivity(intent)
        }
    }//end of function

    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { view, selectedYear, selectedMonth, selectedDay ->
                // Handle the selected date
                val selectedDate = "$selectedYear-${selectedMonth + 1}-$selectedDay"
                editTextDatePicker.setText(selectedDate)

                PrefsHelper.savePrefs(applicationContext, "dob",selectedDate)


            },
            year,
            month,
            day
        )

        datePickerDialog.show()
    }




}//end class