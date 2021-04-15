package com.mahmouddev.trainingproject.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mahmouddev.trainingproject.roomDB.DatabaseHelper
import com.mahmouddev.trainingproject.roomDB.dbUtil.Resource
import com.mahmouddev.trainingproject.roomDB.entities.Student
import kotlinx.coroutines.launch
import kotlin.Exception

class StudentViewModel(var dbHelper: DatabaseHelper) : ViewModel() {
    val TAG = "StudentViewModel"
    private val student = MutableLiveData<Resource<List<Student>>>()
    private val insertStudent = MutableLiveData<Resource<Long>>()
    private val updateStudent = MutableLiveData<Resource<Int>>()
    private val deleteStudent = MutableLiveData<Resource<Int>>()

    fun fetchStudents() {

        viewModelScope.launch {
            student.postValue(Resource.loading(null))

            try {
                student.postValue(Resource.success(dbHelper.getAllStudent()))
            } catch (ex: Exception) {
                student.postValue(Resource.error(ex.message.toString(), null))
                Log.e(TAG, "fetchStudents:${ex.message} ")
            }
        }

    }


    fun insertStudent(student: Student) {
        viewModelScope.launch {
            insertStudent.postValue(Resource.loading(null))
            try {
                val rowId = dbHelper.insertStudent(student)
                insertStudent.postValue(Resource.success(rowId))

            } catch (ex: Exception) {
                insertStudent.postValue(Resource.error(ex.message.toString(), null))

                Log.e(TAG, "insertStudent: ${ex.message}")
            }
        }
    }

    fun updateStudent(student: Student) {
        viewModelScope.launch {
            updateStudent.postValue(Resource.loading(null))
            try {
                val rowId = dbHelper.updateStudent(student)
                updateStudent.postValue(Resource.success(rowId))

            } catch (ex: Exception) {
                updateStudent.postValue(Resource.error(ex.message.toString(), null))
                Log.e(TAG, "updateStudent: ${ex.message}")
            }
        }
    }

    fun deleteStudent(id: Int) {
        viewModelScope.launch {
            deleteStudent.postValue(Resource.loading(null))
            try {
                val rowId = dbHelper.delete(id)
                deleteStudent.postValue(Resource.success(rowId))

            } catch (ex: Exception) {
                deleteStudent.postValue(Resource.error(ex.message.toString(), null))

                Log.e(TAG, "deleteStudent: ${ex.message}")
            }
        }
    }

    fun getStudentsAsLiveData(): LiveData<Resource<List<Student>>> {
        return student
    }

    fun insertAsLiveData(): LiveData<Resource<Long>> {
        return insertStudent
    }

    fun updateAsLiveData(): LiveData<Resource<Int>> {
        return updateStudent
    }

    fun deleteAsLiveData(): LiveData<Resource<Int>> {
        return deleteStudent
    }


}