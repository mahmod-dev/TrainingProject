package com.mahmouddev.trainingproject.roomDB.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "student_tb")
data class Student(
    var name: String,
    var age: String,
    var rate: Int,
    var isGraduate: Boolean = false,
    var gender: String,

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

) : Parcelable