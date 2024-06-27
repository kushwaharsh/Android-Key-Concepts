package com.example.fragmentfundamentals

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.fragmentfundamentals.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var fragmentManager: FragmentManager
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        initViews()
        setListeners()

    }

    private fun setListeners() {
        binding.frag1Btn.setOnClickListener {
            goToFragment(Fragment1())
        }
        binding.frag2Btn.setOnClickListener {
            goToFragment(Fragment2())
        }
    }

    private fun initViews() {
        goToFragment(Fragment1())
    }

    private fun goToFragment(fragment: Fragment){
        fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction().replace(R.id.frameLayoutView , fragment).commit()
    }
}