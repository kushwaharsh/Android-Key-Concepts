package com.example.passfragdata

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.passfragdata.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Add FragmentA initially
        if (savedInstanceState == null) {
            val loginFrag = LoginFragment(this)
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayoutView, loginFrag)
                .commit()
        }
    }

    // Method to switch to FragmentB
    fun switchToProfileFrag(data: Bundle) {
        val profileFrag = ProfileFragment()
        profileFrag.arguments = data

        supportFragmentManager.beginTransaction()
            .replace(R.id.frameLayoutView, profileFrag)
            .addToBackStack(null)
            .commit()
    }

}