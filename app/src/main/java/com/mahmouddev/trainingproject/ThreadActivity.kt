package com.mahmouddev.trainingproject

import android.content.Intent
import android.database.sqlite.SQLiteOpenHelper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.mahmouddev.trainingproject.databinding.ActivityThreadBinding
import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

class ThreadActivity : AppCompatActivity() {
    val TAG = "ThreadActivity"
    lateinit var binding: ActivityThreadBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThreadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.e(TAG, "mainActivity scope: ${Thread.currentThread().name}")

        lifecycleScope.launch {

            for (i in 0..100) {
                Log.e(TAG, "onCreate: $i")
                delay(1000)
            }

        }

        GlobalScope.launch {
            delay(3000)
            startActivity(Intent(this@ThreadActivity, MainActivity::class.java))
            finish()
        }


    }

    suspend fun getDataFromApi(): String {
        delay(3000)
        return "get result 1 from internet1"
    }

    suspend fun getDataFromApi2(result: String): String {
        return "result2 ->: $result"
    }

}