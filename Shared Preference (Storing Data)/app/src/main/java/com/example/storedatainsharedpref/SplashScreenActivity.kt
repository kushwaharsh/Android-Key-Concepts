package com.example.storedatainsharedpref

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler(Looper.getMainLooper()).postDelayed({
            val sharedPref : SharedPreferences = getSharedPreferences("loginData" , Context.MODE_PRIVATE)
            val isLoggedIn = sharedPref.getBoolean("isLoggedIn" , false)

            if (isLoggedIn){
                startActivity(Intent(this , HomeScreenActivity::class.java))
            } else{
                startActivity(Intent(this , LoginScreenActivity::class.java))
            }
            finish()
        }  , 3000)
    }
}