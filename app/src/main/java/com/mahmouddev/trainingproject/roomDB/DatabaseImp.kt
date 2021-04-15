package com.mahmouddev.trainingproject.roomDB

import com.mahmouddev.trainingproject.roomDB.dao.StudentDao
import com.mahmouddev.trainingproject.roomDB.entities.Student

class DatabaseImp(var stdDao:StudentDao): DatabaseHelper {
    override suspend fun getAllStudent(): List<Student> {
        return stdDao.getAllStudent()
    }

    override suspend fun insertStudent(student: Student): Long {
        return stdDao.insertStudent(student)
    }

    override suspend fun updateStudent(student: Student): Int {
       return stdDao.updateStudent(student)
    }

    override suspend fun delete(student: Student): Int {
        return stdDao.delete(student)
    }

    override suspend fun delete(studentId: Int): Int {
        return stdDao.delete(studentId)
    }
}