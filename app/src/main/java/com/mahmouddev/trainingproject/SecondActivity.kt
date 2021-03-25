package com.mahmouddev.trainingproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        val title = findViewById<TextView>(R.id.tvTitle)

        //  val name = intent.extras?.getString("fName")
        // val age = intent.extras?.getString("lName")
        //  title.text = " first name: ${name} \n last name: ${age} "

        val std = intent.extras?.getParcelable<Student>("student")

        val name = std?.name
        val age = std?.age
        val rate = std?.rate
        val subject = std?.subject
        val builder = StringBuilder()

        subject?.forEach { sbName ->

            builder.append("${sbName}, ")
        }

        title.text = "  name: ${name} \n age: ${age}  \n rate: $rate \n subject: ${builder} "
    }
}