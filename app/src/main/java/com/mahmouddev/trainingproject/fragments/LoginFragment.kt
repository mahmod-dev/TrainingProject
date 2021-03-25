package com.mahmouddev.trainingproject.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.mahmouddev.trainingproject.R
import com.mahmouddev.trainingproject.databinding.FragmentLoginBinding

class LoginFragment : Fragment(R.layout.fragment_login) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentLoginBinding.bind(view)
        binding.tvDate.text = ""
    }

}