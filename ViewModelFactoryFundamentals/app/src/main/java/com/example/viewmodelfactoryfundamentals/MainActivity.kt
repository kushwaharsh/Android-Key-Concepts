package com.example.viewmodelfactoryfundamentals

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.viewmodelfactoryfundamentals.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        mainViewModel = ViewModelProvider(this , MainViewModelFactory(18)).get(MainViewModel::class.java)
        setText()

    }

    fun setText(){
        binding.textCounter.text = mainViewModel.count.toString()
    }
    fun increment(v:View){
        mainViewModel.increment()
        setText()
    }
}