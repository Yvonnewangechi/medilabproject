package com.example.medilabmemberapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        bottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when(menuItem.itemId){
                R.id.bottom_home -> { replaceFragment(HomeFragment())}
                R.id.bottom_search -> { replaceFragment(SearchFragment())}
                R.id.bottom_add -> { replaceFragment(DependantFragment())}
                R.id.bottom_cart -> { replaceFragment(CartFragment())}
                R.id.bottom_profile -> { replaceFragment(ProfileFragment())}

            }//end when
            true

        }//end listener

        replaceFragment(HomeFragment())

    } //end create()

    //function to replace a fragment based on menuItem
    private fun replaceFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.frame_container, fragment).commit()
    }
}