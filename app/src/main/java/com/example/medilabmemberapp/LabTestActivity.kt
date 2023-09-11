package com.example.medilabmemberapp

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.medilabmemberapp.adapters.LabAdapter
import com.example.medilabmemberapp.adapters.LabTestAdapter
import com.example.medilabmemberapp.constants.Constants
import com.example.medilabmemberapp.helpers.ApiHelper
import com.example.medilabmemberapp.helpers.NetworkHelper
import com.example.medilabmemberapp.helpers.PrefsHelper
import com.example.medilabmemberapp.models.Lab
import com.example.medilabmemberapp.models.LabTest
import com.google.gson.GsonBuilder
import org.json.JSONArray
import org.json.JSONObject

class LabTestActivity : AppCompatActivity() {

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



    lateinit var itemList :List<LabTest>
    lateinit var RecyclerView : RecyclerView
    lateinit var LabTestAdapter : LabTestAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lab_test)


        if(NetworkHelper.isInternetConnected(applicationContext)){
            Toast.makeText(applicationContext, "You Are Connected", Toast.LENGTH_SHORT).show()

        }
        else{
            Toast.makeText(applicationContext, "You are not connected", Toast.LENGTH_SHORT).show()

            showAlertDialog()
        }

        RecyclerView =findViewById(R.id.recyclerView)
        LabTestAdapter = LabTestAdapter(applicationContext)
        RecyclerView.layoutManager = LinearLayoutManager(applicationContext)
        //call a function to fetch data
        fetchData()
    }

    fun fetchData(){
        //The function fetches data from the Api
        val api = Constants.BASE_URL + "/lab_tests"
        val helper = ApiHelper(applicationContext)
        val body = JSONObject()

        //static Lab_id variable,to be replaced with the id from the search fragment
        //replace with dynamic values from the preferences
        val lab_id = PrefsHelper.getPrefs(applicationContext,"lab_id")

        body.put("lab_id",lab_id)
       helper.post(api,body, object :ApiHelper.CallBack{
           override fun onSuccess(result: JSONArray?) {
             //Lab test are returned as JSONArray
               val gson = GsonBuilder().create()
               itemList = gson.fromJson(result.toString(),Array<LabTest>::class.java).toList()
               LabTestAdapter.setListItems(itemList)
               RecyclerView.adapter = LabTestAdapter
           }

           override fun onSuccess(result: JSONObject?) {
               Toast.makeText(applicationContext, "No lab Test found", Toast.LENGTH_SHORT).show()
           }

           override fun onFailure(result: String?) {
               Toast.makeText(applicationContext, "Error......", Toast.LENGTH_SHORT).show()
           }
       })
    }

    fun filter(text:String){
        //create an array list to filter our labs
        val filteredList :ArrayList<LabTest> = ArrayList()

        //loop through the itemList
        for (item in itemList){
            if (item.test_name.lowercase().contains(text.lowercase())){
                filteredList.add(item)
            }
        }
        LabTestAdapter.filterList(filteredList)
    } //end filter function

}