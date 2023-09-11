package com.example.medilabmemberapp

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.medilabmemberapp.adapters.LabAdapter
import com.example.medilabmemberapp.adapters.LabTestAdapter
import com.example.medilabmemberapp.constants.Constants
import com.example.medilabmemberapp.helpers.ApiHelper
import com.example.medilabmemberapp.models.Lab
import com.example.medilabmemberapp.models.LabTest
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

        // find the search view based on the Id
        val edSearch :EditText = view.findViewById(R.id.search)
        edSearch.addTextChangedListener(object:TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(textChanged: CharSequence?, p1: Int, p2: Int, p3: Int) {
                filter(textChanged.toString())

            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })
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
     fun filter(text:String){
        //create an array list to filter our labs
        val filteredList :ArrayList<Lab> = ArrayList()

        //loop through the itemList
        for (item in itemList){
            if (item.lab_name.lowercase().contains(text.lowercase())){
                filteredList.add(item)
            }
        }
        LabAdapter.filterList(filteredList)
    } //end filter function










}


