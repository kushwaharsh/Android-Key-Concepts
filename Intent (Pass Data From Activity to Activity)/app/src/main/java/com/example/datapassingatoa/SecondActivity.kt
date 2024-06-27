package com.example.datapassingatoa

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.datapassingatoa.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //RECIVING THE VALUES FROM INTENT AND STORING THEM SETTING THE VALUES IN VIEW
        binding.showName.text = intent.getStringExtra("username").toString()
        binding.showEmail.text = intent.getStringExtra("useremail").toString()
        binding.showPhnos.text = intent.getStringExtra("userphoneno").toString()
        binding.showEligibility.text = intent.getStringExtra("userterms").toString()


    }
}