package com.mahmouddev.trainingproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.e(TAG, "onCreate: ")
        val btn = findViewById<Button>(R.id.btn)
        val etFirstName = findViewById<EditText>(R.id.etFirstName)
        val etLastName = findViewById<EditText>(R.id.etLastName)

        btn.setOnClickListener {
            val fName = etFirstName.text.toString()
            val lName = etLastName.text.toString()
            navigateWithDataActivity(fName, lName)
        }


    }

    private fun navigateToSecondActivity() {
        val intent = Intent(this, SecondActivity::class.java)
        startActivity(intent)
    }

    private fun navigateWithDataActivity(fName: String, lName: String) {
        val intent = Intent(this, SecondActivity::class.java)
        intent.putExtra("fName", fName)
        intent.putExtra("lName", lName)
        startActivity(intent)
    }
    private fun navigateSerializeDataActivity() {
       val std =  Student("ali",20,90.5, arrayOf("math","english","programminig","sientific"))
        val intent = Intent(this, SecondActivity::class.java)
        intent.putExtra("student",std)

        startActivity(intent)
    }


    override fun onStart() {
        super.onStart()
        Log.e(TAG, "onStart: ")
    }

    override fun onResume() {
        super.onResume()
        Log.e(TAG, "onResume: ")
    }

    override fun onPause() {
        super.onPause()
        Log.e(TAG, "onPause: ")
    }

    override fun onStop() {
        super.onStop()
        Log.e(TAG, "onStop: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(TAG, "onDestroy: ")
    }


}