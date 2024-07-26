package com.example.chatapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.chatapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    companion object {
        const val NICKNAME = "usernickname"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.enterchat.setOnClickListener {
            // If the nickname is not empty, go to ChatBoxActivity and add the nickname to the intent extra
            val nicknameText = binding.EnterYourNickname.text.toString()
            if (nicknameText.isNotEmpty()) {
                val intent = Intent(this@MainActivity, ChatBoxActivity::class.java)
                // Retrieve nickname from EditText and add it to the intent extra
                intent.putExtra(NICKNAME, nicknameText)
                startActivity(intent)
            }
        }
    }
}
