package com.example.medilabmemberapp

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.medilabmemberapp.helpers.PrefsHelper

class HomeFragment : Fragment() {
    lateinit var tvWelcome :TextView
    lateinit var btnLoggedIn : Button
    lateinit var tvNotLogged :TextView
    lateinit var btnLogOut :Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        tvWelcome = view.findViewById(R.id.text_welcome)
        btnLoggedIn = view.findViewById(R.id.btnNotLoggedIn)
        tvNotLogged = view.findViewById(R.id.TextNotLogged)
        btnLogOut = view.findViewById(R.id.btnNotLoggedOut)
        updateUI()
        return view
    }

    //function to perfom UI update based on the login

    fun updateUI(){
        tvWelcome.visibility = View.GONE
        btnLoggedIn.visibility = View.GONE
        tvNotLogged.visibility = View.GONE
        btnLogOut.visibility = View.GONE

        //get refresh_token to confirm the login

        val token = PrefsHelper.getPrefs(requireContext(), "refresh_token")
        if(token.isEmpty()){

            tvNotLogged.visibility = View.VISIBLE
            btnLoggedIn.visibility = View.VISIBLE
            btnLoggedIn.setOnClickListener {
                val  intent = Intent(requireContext(), MemberSignIn::class.java)
                startActivity(intent)
            }
        }
        else{
            val surname = PrefsHelper.getPrefs(requireContext(), "LoggedSurname")
            tvWelcome.text = "Welcome $surname"
            tvWelcome.visibility = View.VISIBLE
            btnLogOut.visibility = View.VISIBLE
            btnLogOut.setOnClickListener {
                PrefsHelper.clearPrefs(requireContext())
                val  intent = Intent(requireContext(), HomeFragment::class.java)
                startActivity(intent)

            }
        }



    }


}