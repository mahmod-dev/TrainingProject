package com.mahmouddev.trainingproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.mahmouddev.trainingproject.databinding.ActivityViewModelBinding
import com.mahmouddev.trainingproject.util.GenerateNumber
import com.mahmouddev.trainingproject.util.GenerateNumberLiveData

class ViewModelActivity : AppCompatActivity() {
    lateinit var binding: ActivityViewModelBinding
    var number: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewModelBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //  saveByOnSaveInstance(savedInstanceState)

        val viewModel = ViewModelProvider(this).get(GenerateNumber::class.java)
        binding.tvNumber.text =viewModel.getNumber().toString()

        //fetchAsLiveData()

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("number", number)
    }

    private fun saveByOnSaveInstance(bundle: Bundle?) {
        val generateNumber = GenerateNumber()
        number = generateNumber.getNumber()
        if (bundle != null) {
            val bundleNumber = bundle.getInt("number")
            binding.tvNumber.text = bundleNumber.toString()
            number = bundleNumber

        } else {
            binding.tvNumber.text = number.toString()
        }

    }

    private fun fetchAsLiveData() {
        val viewModel = ViewModelProvider(this).get(GenerateNumberLiveData::class.java)
        viewModel.getNumber()
        viewModel.getNumberAsLiveData().observe(this, {
            binding.tvNumber.text = it.toString()

        })

    }

}