package com.mahmouddev.trainingproject.roomDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mahmouddev.trainingproject.roomDB.dao.StudentDao
import com.mahmouddev.trainingproject.roomDB.entities.Student

@Database(entities = [Student::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun daoStudent(): StudentDao

    companion object {
        var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = buildDatabase(context)
                    return INSTANCE!!
                }
            } else {
                return INSTANCE!!
            }

        }


        fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, "studentsDB").build()
        }
    }
}