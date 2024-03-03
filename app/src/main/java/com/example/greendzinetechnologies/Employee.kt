package com.example.greendzinetechnologies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.example.greendzinetechnologies.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class Employee : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employee)

        replaceFragment(Home())
        val bottomnav = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val menu = bottomnav.menu
        for (i in 0 until menu.size()) {
            val menuItem = menu.getItem(i)
            menuItem.title = "" // Clear the title
        }
         bottomnav.setOnItemSelectedListener{
             Log.i("iddddd: ","${it}")
             when(it.itemId){

                 R.id.home_nav -> replaceFragment(Home())
                 R.id.profile_nav -> replaceFragment(Profile())

                 else ->{

                 }
             }
             true
         }
    }

    private fun replaceFragment(fragment : Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.framelayout,fragment)
        fragmentTransaction.commit()
    }
}