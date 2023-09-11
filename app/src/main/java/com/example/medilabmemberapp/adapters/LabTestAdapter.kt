package com.example.medilabmemberapp.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import com.example.medilabmemberapp.LabTestActivity
import com.example.medilabmemberapp.R
import com.example.medilabmemberapp.SingleLabTest
import com.example.medilabmemberapp.helpers.PrefsHelper
import com.example.medilabmemberapp.models.Lab
import com.example.medilabmemberapp.models.LabTest

class LabTestAdapter (var context: Context) :
    RecyclerView.Adapter<LabTestAdapter.ViewHolder>(){
    //declare a variable of type List<Model> empty

    var itemList :List<LabTest> = listOf() //its empty

    inner class ViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView)

    //adapter classes implements 3 methods

    //1.oncreateviewHolder :  used to call the single item file
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //access/inflate the single_lab.xml
        val view  = LayoutInflater.from(parent.context).inflate(R.layout.single_lab_test,
            parent,false)
        return ViewHolder(view)
    }

    //2.onbindview:used to attach data to the view

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //find the views from single_lab.xml and bind data from the model
        val tvLabTestName :TextView = holder.itemView.findViewById(R.id.tvLabTest)
        val tvLabTestAvailability :TextView = holder.itemView.findViewById(R.id.TvLabTestAvailability)
        val tvLabTestCost :TextView = holder.itemView.findViewById(R.id.TvLabTestCost)

        //Assume a single lab from the list of labs(itemList)

        val singleLab = itemList[position]

        //bind data to single lab
        tvLabTestName.text = singleLab.test_name
        tvLabTestCost.text = "KSH " +singleLab.test_cost.toString()
        tvLabTestAvailability.text =  "Availability: " +singleLab.availability

        //Clicking on a lab test and proceeding to singleLabTest Activity with data from the model

        holder.itemView.setOnClickListener {
            //save data from a particlar lab tests to prefs
            val intent = Intent(context,SingleLabTest::class.java)
            intent.putExtra("lab_id",singleLab.lab_id)
            intent.putExtra("test_name",singleLab.test_name)
            intent.putExtra("test_discount",singleLab.test_discount)
            intent.putExtra("test_cost",singleLab.test_cost)
            intent.putExtra("test_description",singleLab.test_description)
            intent.putExtra("availability",singleLab.availability)
            intent.putExtra("more_info",singleLab.more_info)
            intent.putExtra("reg_date",singleLab.reg_date)

            intent.flags  = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }



    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    //custom functions
    //1.filter the itemList(contains the list of all labs)
    //used for searching

    fun filterList(filterList: List<LabTest>){
        itemList = filterList
        notifyDataSetChanged()
    }
    //earlier the itemList is empty
    //the function will get data from the Api and pass to the itemlist

    fun setListItems(data: List<LabTest>){
        itemList = data
        notifyDataSetChanged()
    }
}