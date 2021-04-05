package com.mahmouddev.trainingproject.util

import androidx.lifecycle.ViewModel

class GenerateNumber : ViewModel(){

    private val number: Int? = null

    fun getNumber(): Int {
        if (number == null) {
            return generateRandomNumber()
        } else {
            return number
        }
    }

    private fun generateRandomNumber(): Int {
        return (0..100).random()
    }
}