package com.example.recyclerviewclicklistner

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.recyclerviewclicklistner.databinding.ActivityCompanyDetailBinding
import com.example.recyclerviewclicklistner.databinding.ActivityMainBinding

class CompanyDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCompanyDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCompanyDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.detailCompanyLogo.setImageResource(intent.getIntExtra("image" , R.drawable.x_ic))
        binding.detailCompanyName.text = intent.getStringExtra("name").toString()
        binding.detailCompanyDescription.text = intent.getStringExtra("description")


        binding.mainPageBtn.setOnClickListener {
            startActivity(Intent(this@CompanyDetailActivity, MainActivity::class.java))

        }
    }
}