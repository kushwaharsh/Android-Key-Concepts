package com.example.passfragdata

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.passfragdata.databinding.FragmentProfileBinding

class ProfileFragment() : Fragment() {
    private  lateinit var binding: FragmentProfileBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentProfileBinding.inflate(inflater , container , false)
        val view = binding.root

        val name = arguments?.getString("userName")
        val email = arguments?.getString("userEmail")
        val phoneno = arguments?.getString("userPhone")
        val tncEligibility = arguments?.getString("userTnC")

        binding.showName.text = name
        binding.showEmail.text = email
        binding.showPhnos.text = phoneno
        binding.showEligibility.text = tncEligibility

        return view
    }


}