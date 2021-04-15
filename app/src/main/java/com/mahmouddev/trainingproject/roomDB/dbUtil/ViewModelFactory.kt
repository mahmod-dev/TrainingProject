package com.mahmouddev.trainingproject.roomDB.dbUtil

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mahmouddev.trainingproject.retrofit.ApiHelper
import com.mahmouddev.trainingproject.roomDB.DatabaseHelper
import com.mahmouddev.trainingproject.viewmodel.RetrofitViewModel
import com.mahmouddev.trainingproject.viewmodel.StudentViewModel


class ViewModelFactory(private val dbHelper: DatabaseHelper?,var apiHelper: ApiHelper?) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(StudentViewModel::class.java)) {
            return StudentViewModel(dbHelper!!) as T
        }

        if (modelClass.isAssignableFrom(RetrofitViewModel::class.java)) {
            return RetrofitViewModel(apiHelper!!) as T
        }

        throw IllegalArgumentException("Unknown class name")
    }


}
