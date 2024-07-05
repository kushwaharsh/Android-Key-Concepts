package com.example.bindingadaptersmvvm

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.example.bindingadaptersmvvm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this , R.layout.activity_main)

        val post = Post("Introduction to Binding Adapters",
                   "Here is the detailed description of the topic",
                          "https://media.istockphoto.com/id/1937206941/photo/system-hacked-warning-alert-on-laptop-smartphone-cyber-attack-on-computer-network-virus.jpg?s=612x612&w=0&k=20&c=5m92PAzacRIoG3zWnFQtn9FNSr2_-4FMorsj7oUb4Q4=")

        binding.post = post
    }
}