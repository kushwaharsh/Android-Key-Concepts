package com.example.passfragdata

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.passfragdata.databinding.FragmentLoginBinding

class LoginFragment(mainActivity: MainActivity) : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inflate the layout for this fragment using binding
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.loginBtn.setOnClickListener {
            val data = Bundle().apply {
                putString("userName", binding.etUsername.text.toString())
                putString("userEmail", binding.etEmail.text.toString())
                putString("userPhone", binding.etPhno.text.toString())
                putString("userTnC", binding.tncCheckbox.isChecked.toString())
            }

            (activity as MainActivity).switchToProfileFrag(data)

        }
        return view
    }
}