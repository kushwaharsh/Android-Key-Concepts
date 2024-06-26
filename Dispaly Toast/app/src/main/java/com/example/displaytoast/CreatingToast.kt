package com.example.displaytoast

import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.displaytoast.databinding.ActivityCreatingToastBinding

class CreatingToast : AppCompatActivity() {
    private lateinit var binding : ActivityCreatingToastBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityCreatingToastBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //This is the normal toast display
        binding.helloBtn.setOnClickListener{
            Toast.makeText(this ,"This is a Hello Toast" , Toast.LENGTH_SHORT).show()
        }

        //this is the Customised Position Toast
        binding.happySundayBtn.setOnClickListener {
            val toast = Toast.makeText(this, "Custom position Toast", Toast.LENGTH_SHORT)
            toast.setGravity(Gravity.CENTER , 100, 100)
            toast.show()
        }

        //This is the normal toast display
        binding.googMorningBtm.setOnClickListener{
            Toast.makeText(this ,"This is a Gud Morinig Toast" , Toast.LENGTH_SHORT).show()
        }

    }
}