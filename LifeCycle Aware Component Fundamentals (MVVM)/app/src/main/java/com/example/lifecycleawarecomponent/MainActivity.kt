package com.example.lifecycleawarecomponent

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        lifecycle.addObserver(Observer())
        Log.d("MAIN" , "ACTIVITY ON-CREATE")
    }

    override fun onResume() {
        super.onResume()
        Log.d("MAIN" , "ACTIVITY ON-RESUME")
    }

    override fun onPause() {
        super.onPause()
        Log.d("MAIN" , "ACTIVITY ON-PAUSE")
    }

    override fun onStop() {
        super.onStop()
        Log.d("MAIN" , "ACTIVITY ON-STOP")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MAIN" , "ACTIVITY ON-DESTROY")
    }
}