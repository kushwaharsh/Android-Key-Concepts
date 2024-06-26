package com.example.storedatainsharedpref

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.storedatainsharedpref.databinding.ActivityHomeScreenBinding

class HomeScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val sharedPref :SharedPreferences = getSharedPreferences("loginData" , MODE_PRIVATE)
        val userName = sharedPref.getString("name" , null)
        val userEmail = sharedPref.getString("email" , null)

        binding.showUsername.text = userName
        binding.showEmail.text = userEmail

        binding.logoutBtn.setOnClickListener {
            val editor = sharedPref.edit()
            editor.putBoolean("isLoggedIn", false)
            editor.apply()

            // Move to Login Activity
            startActivity(Intent(this, LoginScreenActivity::class.java))
            finish()
        }

    }
}