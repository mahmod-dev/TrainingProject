package com.mahmouddev.trainingproject

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import org.jetbrains.annotations.NotNull

@Parcelize
 data class Student(
    val name: String,
    val age: Int,
    val rate: Double,
    val subject : Array<String>? = null

    ): Parcelable