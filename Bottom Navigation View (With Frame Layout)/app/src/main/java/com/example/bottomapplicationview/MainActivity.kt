package com.example.bottomapplicationview

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.bottomapplicationview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //SETTING UP THE INITIAL HOME FRAGMENT
        replaceFragment(HomeFragment())

        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                     R.id.home -> replaceFragment(HomeFragment())
                     R.id.bag -> replaceFragment(BagFragment())
                     R.id.account -> replaceFragment(AccountFragment())
                     R.id.setting -> replaceFragment(SettingFragment())

                else -> {
                    Toast.makeText(this , "Something Went Wrong" , Toast.LENGTH_SHORT).show()
                }
            }
            true
        }

    }

    //MAKE THE FUNCTION TO REPLACE THE FRAGMENT
    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout , fragment)
        fragmentTransaction.commit()
    }
}