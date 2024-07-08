package com.example.customtablayout

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.customtablayout.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    lateinit var viewPager: ViewPager2
    lateinit var adapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        adapter = ViewPagerAdapter(this)
        binding.viewPager.adapter =adapter

        TabLayoutMediator(binding.tabLayout , binding.viewPager){tab , position ->
            tab.text = when(position){
                0 -> "Home"
                1 -> "Profile"
                2 -> "Settings"
                else -> null
            }
        }.attach()
        // Apply the custom background to each tab
        for (i in 0 until binding.tabLayout.tabCount) {
            val tab = binding.tabLayout.getTabAt(i)
            val tabView = tab?.view
            tabView?.setBackgroundResource(R.drawable.tab_indicator)
        }

        // Update tab background when selected/unselected
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                tab.view.setBackgroundResource(R.drawable.tab_indicator)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                tab.view.setBackgroundResource(R.drawable.tab_indicator)
            }

            override fun onTabReselected(tab: TabLayout.Tab) {
                // Do nothing
            }
        })

    }
}