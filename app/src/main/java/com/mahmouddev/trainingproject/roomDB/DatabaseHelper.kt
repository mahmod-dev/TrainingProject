package com.mahmouddev.trainingproject.roomDB

import com.mahmouddev.trainingproject.roomDB.entities.Student

interface DatabaseHelper {

    suspend fun getAllStudent(): List<Student>

    suspend fun insertStudent(student: Student): Long

    suspend fun updateStudent(student: Student): Int

    suspend fun delete(student: Student): Int

    suspend fun delete(studentId: Int): Int

}