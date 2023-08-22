package com.example.medilabmemberapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.medilabmemberapp.helpers.PrefsHelper

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //find the three edit texts and get data from them
        val editName = findViewById<EditText>(R.id.name)
        val editAge = findViewById<EditText>(R.id.age)
        val editCourse = findViewById<EditText>(R.id.course)

        val name = editName.text
        val age = editAge.text
        val course = editCourse.text

        //find button save and trigger save operation

        val buttonSave = findViewById<Button>(R.id.save)
        buttonSave.setOnClickListener {
            PrefsHelper.savePrefs(applicationContext, "name",name.toString())
            PrefsHelper.savePrefs(applicationContext, "age",age.toString())
            PrefsHelper.savePrefs(applicationContext, "course",course.toString())
            Toast.makeText(applicationContext, "saved successfully", Toast.LENGTH_SHORT).show()

        }

        //find the button get and display the data on a toast
        val getButton = findViewById<Button>(R.id.get)
        getButton.setOnClickListener {
            val receivedName = PrefsHelper.getPrefs(applicationContext,"name")
            val receivedAge = PrefsHelper.getPrefs(applicationContext,"age")
            val receivedCourse = PrefsHelper.getPrefs(applicationContext,"course")

            Toast.makeText(applicationContext, "Your name is:$receivedName", Toast.LENGTH_SHORT).show()
            Toast.makeText(applicationContext, "Your age is:$receivedAge", Toast.LENGTH_SHORT).show()
            Toast.makeText(applicationContext, "Your course is:$receivedCourse", Toast.LENGTH_SHORT).show()
        }

       //find the clear button and clear the preference
        val clearButton = findViewById<Button>(R.id.clear)
        clearButton.setOnClickListener {
            PrefsHelper.clearPrefs(applicationContext)

            Toast.makeText(applicationContext, "cleared successfully", Toast.LENGTH_SHORT).show()


        }



    }
}
//shared preferences: temporary storage on an application
//facebook,saves your username and password
// data is available ,even if the app is closed until the preferences is cleared