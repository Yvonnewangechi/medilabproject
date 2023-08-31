package com.example.medilabmemberapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.medilabmemberapp.adapters.LabAdapter
import com.example.medilabmemberapp.constants.Constants
import com.example.medilabmemberapp.helpers.ApiHelper
import com.example.medilabmemberapp.models.Lab
import com.google.gson.GsonBuilder
import org.json.JSONArray
import org.json.JSONObject


class SearchFragment : Fragment() {

    //outside
    //declare the resources to be used without assigning values
    lateinit var itemList :List<Lab>
    lateinit var RecyclerView :RecyclerView
    lateinit var LabAdapter :LabAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // This acts like the oncreate function in activities
        //1.find recyclerView

        val view = inflater.inflate(R.layout.fragment_search, container, false)
        RecyclerView =view.findViewById(R.id.recyclerView)
        LabAdapter = LabAdapter(requireContext())
        RecyclerView.layoutManager = LinearLayoutManager(context)
        //call a function to fetch data
        fetchData()
        return view


    } //end onCreateView()
    fun fetchData(){
        //The function fetches data from the Api
        val api = Constants.BASE_URL + "/laboratories"
        val helper = ApiHelper(requireContext())
        helper.get(api, object :ApiHelper.CallBack{
            override fun onSuccess(result: JSONArray?) {
                //results is our JSONArray response data
                //we need a library to convert JSONArray to List<Lab>
                //we use gson library
                val gson = GsonBuilder().create()
                itemList = gson.fromJson(result.toString(), Array<Lab>::class.java).toList()
                LabAdapter.setListItems(itemList)
                RecyclerView.adapter = LabAdapter
            }

            override fun onSuccess(result: JSONObject?) {
                TODO("Not yet implemented")
            }

            override fun onFailure(result: String?) {
                Toast.makeText(requireContext(), "${result.toString()}", Toast.LENGTH_SHORT).show()
            }
        })
    }


}