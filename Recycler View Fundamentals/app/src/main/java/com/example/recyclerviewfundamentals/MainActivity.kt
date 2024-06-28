package com.example.recyclerviewfundamentals

import android.os.Bundle
import android.provider.ContactsContract.Data
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewfundamentals.databinding.ActivityMainBinding
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private var dataList: ArrayList<DataClass> = ArrayList()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerView = binding.myRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        //each layout of recycler view have the same height and weight
        recyclerView.setHasFixedSize(true)
        parseData()
    }

    private fun parseData() {
        dataList.add(DataClass(R.drawable.x_ic, "X Corporation", "X Corporation: A leading tech conglomerate known for its innovative solutions."))
        dataList.add(DataClass(R.drawable.insta_ic, "Instagram.com", "Instagram.com: A popular social media platform for sharing photos and videos."))
        dataList.add(DataClass(R.drawable.fb_ic, "Facebook.com", "Facebook.com: A global social networking site connecting people worldwide."))
        dataList.add(DataClass(R.drawable.youtube_ic, "YouTube.com", "YouTube.com: The largest video-sharing platform with diverse content."))
        dataList.add(DataClass(R.drawable.snap_ic, "Snapchat.com", "Snapchat.com: A multimedia messaging app known for its disappearing messages."))
        dataList.add(DataClass(R.drawable.skype_ic, "Skype Corporation", "Skype Corporation: A communication tool offering video and voice calls."))
        dataList.add(DataClass(R.drawable.twitter_ic, "Twitter.com", "Twitter.com: A social networking service for short posts called tweets."))
        dataList.add(DataClass(R.drawable.telegram_ic, "Telegram.com", "Telegram.com: A cloud-based instant messaging app focused on speed and security."))
        dataList.add(DataClass(R.drawable.reddit_ic, "Reddit.com", "Reddit.com: An online community and discussion forum with diverse topics."))
        dataList.add(DataClass(R.drawable.discord_ic, "Discord.com", "Discord.com: A platform for voice, video, and text communication among communities."))
        dataList.add(DataClass(R.drawable.linked_ic, "LinkedIn Corporation", "LinkedIn Corporation: A professional networking site for career development."))
        dataList.add(DataClass(R.drawable.google_ic, "Google.com", "Google.com: A leading search engine and tech company offering various services."))
        dataList.add(DataClass(R.drawable.cult_ic, "Cult Ltd", "Cult Ltd: A fitness and wellness company promoting a healthy lifestyle."))

        recyclerView.adapter = AdapterClass(dataList)
    }

}