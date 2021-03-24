package com.mahmouddev.trainingproject

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
 class Student(
    val name: String,
    val age: Int,
    val rate: Double,
    val subject : Array<String>

    ): Parcelable