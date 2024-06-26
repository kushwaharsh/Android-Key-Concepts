package com.example.storedatainsharedpref

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.storedatainsharedpref.databinding.ActivityLoginScreenBinding

class LoginScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPref = getSharedPreferences("loginData", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()

        binding.loginBtn.setOnClickListener {
            val name = binding.etUsername.text.toString()
            val email = binding.etEmail.text.toString()

            if (name.isEmpty()) {
                Toast.makeText(this, "Username cannot be empty", Toast.LENGTH_SHORT).show()
            } else if (email.isEmpty()) {
                Toast.makeText(this, "Email cannot be empty", Toast.LENGTH_SHORT).show()
            } else {
                editor.apply {
                    putString("name", name)
                    putString("email", email)
                    putBoolean("isLoggedIn", true)
                    apply()
                }
                startActivity(Intent(this, HomeScreenActivity::class.java))
                finish()
            }
        }
    }
}
