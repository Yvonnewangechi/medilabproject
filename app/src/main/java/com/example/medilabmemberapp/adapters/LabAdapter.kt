package com.example.medilabmemberapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import com.example.medilabmemberapp.R
import com.example.medilabmemberapp.models.Lab

class LabAdapter (var context: Context) :
    RecyclerView.Adapter<LabAdapter.ViewHolder>(){
        //declare a variable of type List<Model> empty

        var itemList :List<Lab> = listOf() //its empty

    inner class ViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView)

    //adapter classes implements 3 methods

    //1.oncreateviewHolder :  used to call the single item file
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //access/inflate the single_lab.xml
        val view  = LayoutInflater.from(parent.context).inflate(R.layout.single_lab,
            parent,false)
        return ViewHolder(view)
    }

    //2.onbindview:used to attach data to the view

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //find the views from single_lab.xml and bind data from the model
        val tvLabName :TextView = holder.itemView.findViewById(R.id.textViewLabName)
        val tvLabPermit :TextView = holder.itemView.findViewById(R.id.TvLabPermit)
        val tvLabPhone :TextView = holder.itemView.findViewById(R.id.TvLabPhone)

        //Assume a single lab from the list of labs(itemList)

        val singleLab = itemList[position]

        //bind data to single lab
        tvLabName.text = singleLab.lab_name
        tvLabPermit.text = singleLab.permit_id
        tvLabPhone.text =  "Tel:" +singleLab.phone

    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    //custom functions
    //1.filter the itemList(contains the list of all labs)
    //used for searching

    fun filterList(filterList: List<Lab>){
        itemList = filterList
        notifyDataSetChanged()
    }
    //earlier the itemList is empty
    //the function will get data from the Api and pass to the itemlist

    fun setListItems(data: List<Lab>){
        itemList = data
        notifyDataSetChanged()
    }
}