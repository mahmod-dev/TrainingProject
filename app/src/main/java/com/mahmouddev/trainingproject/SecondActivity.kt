package com.mahmouddev.trainingproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        val name = intent.extras?.getString("fName")
        val age = intent.extras?.getString("lName")

        val title = findViewById<TextView>(R.id.tvTitle)

        title.text = " first name: ${name} \n last name: ${age} "

    }
}