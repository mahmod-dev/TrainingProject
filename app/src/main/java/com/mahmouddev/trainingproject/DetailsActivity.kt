package com.mahmouddev.trainingproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val std = intent.extras?.getParcelable<Student>("student")
        Toast.makeText(this, "${std} " , Toast.LENGTH_LONG).show()
    }
}