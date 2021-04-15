package com.mahmouddev.trainingproject.roomDB.dao

import androidx.room.*
import com.mahmouddev.trainingproject.roomDB.entities.Student

@Dao
interface StudentDao {

    @Query("SELECT * FROM student_tb")
    suspend fun getAllStudent(): List<Student>

    @Insert
    suspend fun insertStudent(student: Student): Long

    @Update
    suspend fun updateStudent(student: Student): Int

    @Delete
    suspend fun delete(student: Student): Int

    @Query("DELETE FROM student_tb WHERE id = :studentId")
    suspend fun delete(studentId: Int): Int

}