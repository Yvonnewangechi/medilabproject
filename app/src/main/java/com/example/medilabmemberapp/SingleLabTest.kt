package com.example.medilabmemberapp

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.medilabmemberapp.helpers.NetworkHelper
import com.example.medilabmemberapp.helpers.SQLiteCartHelper

class SingleLabTest : AppCompatActivity() {
    private fun showAlertDialog(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Internet Check")
            .setMessage("Please Check your internet connection")
            .setPositiveButton("Yes"){dialog, which ->
                dialog.dismiss()
            }



        val alertDialog: AlertDialog = builder.create()
        alertDialog.show()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_lab_test)

        if(NetworkHelper.isInternetConnected(applicationContext)){
            Toast.makeText(applicationContext, "You Are Connected", Toast.LENGTH_SHORT).show()

        }
        else{
            Toast.makeText(applicationContext, "You are not connected", Toast.LENGTH_SHORT).show()

            showAlertDialog()
        }
        //Get data from the intent extras and bind them to the views

        val  test_id = intent.extras?.getString("test_id")
        //to be used in the cart
        val tvTestName:TextView = findViewById(R.id.tvLabTestName)
        val test_name = intent.extras?.getString("test_name")
        tvTestName.text = test_name
        //=======

        val tvTestCost : TextView =findViewById(R.id.cost)
        val test_cost = intent.extras?.getInt("test_cost")
        tvTestCost.text ="Cost :"+test_cost.toString()
        //=======

        val tvTestDiscount : TextView =findViewById(R.id.discount)
        val test_discount = intent.extras?.getInt("test_discount")
        tvTestDiscount.text ="Discount :"+test_discount.toString()

        val tvTestDesc : TextView =findViewById(R.id.textDec)
        val test_desc = intent.extras?.getString("test_description")
        tvTestDesc.text =test_desc

        val tvTestMore_info :TextView = findViewById(R.id.more_info)
        val more_info = intent.extras?.getString("more_info")
        tvTestMore_info.text = more_info
//testing save cart
        val cart = findViewById<Button>(R.id.cart)
        cart.setOnClickListener {

            val intent = Intent(applicationContext,MyCart::class.java)
            startActivity(intent)
        }







    }
}