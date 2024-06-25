package com.example.splashscreenapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class HomeScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Android now provides the API for creating the Splash Screen, This is using that APL
        //refer gradle file for Adding Dependency
        Thread.sleep(3000)
        installSplashScreen()
        setContentView(R.layout.activity_home_screen)

    }
}