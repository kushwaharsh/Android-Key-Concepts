package com.example.databindingfundamentals

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.databindingfundamentals.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var quoteObj: Quote
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this , R.layout.activity_main)

        quoteObj = Quote("Quote shown Here" , "Author Name Shown Here")

        binding.quote = quoteObj

    }
}