package com.mahmouddev.trainingproject.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GenerateNumberLiveData : ViewModel() {

    private var number: MutableLiveData<Int>? = null

    fun getNumber(): MutableLiveData<Int> {

        if (number == null) {
            number = MutableLiveData()
            return generateRandomNumber()
        } else {
            return number!!
        }
    }

    private fun generateRandomNumber(): MutableLiveData<Int> {
        number?.value = (0..100).random()

        return number!!
    }

    fun getNumberAsLiveData(): LiveData<Int> {
        return number!!
    }


}