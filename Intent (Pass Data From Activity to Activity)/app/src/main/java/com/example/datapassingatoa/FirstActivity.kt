package com.example.datapassingatoa

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.datapassingatoa.databinding.ActivityFirstBinding

class FirstActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFirstBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginBtn.setOnClickListener {

            //FINDING THE VIEW USING VIEW BINDING
            val username = binding.etUsername.text.toString().trim()
            val useremail = binding.etEmail.text.toString().trim()
            val userphoneno = binding.etPhno.text.toString().trim()
            val userterms = binding.tncCheckbox.isChecked

            // VALIDATING INPUT
            if (username.isEmpty()) {
                binding.etUsername.error = "Username is required"
                return@setOnClickListener
            }
            if (useremail.isEmpty()) {
                binding.etEmail.error = "Email is required"
                return@setOnClickListener
            } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(useremail).matches()) {
                binding.etEmail.error = "Enter a valid email"
                return@setOnClickListener
            }
            if (userphoneno.isEmpty()) {
                binding.etPhno.error = "Phone number is required"
                return@setOnClickListener
            } else if (!userphoneno.matches("\\d{10}".toRegex())) {
                binding.etPhno.error = "Enter a valid phone number"
                return@setOnClickListener
            }
            if (!userterms) {
                Toast.makeText(this, "You must agree to the terms and conditions", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            //SENDING THE DATA VIA INTENT
            val intent = Intent(this, SecondActivity::class.java).apply {
                putExtra("username", username)
                putExtra("useremail", useremail)
                putExtra("userphoneno", userphoneno)
                putExtra("userterms", userterms.toString())
            }

            startActivity(intent)
            finish()
        }
    }
}
