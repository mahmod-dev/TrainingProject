package com.mahmouddev.trainingproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.mahmouddev.trainingproject.adapters.StudentAdapter
import com.mahmouddev.trainingproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val TAG = "MainActivity"
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btn.setOnClickListener {
            val fName = binding.etFirstName.text.toString()
            val lName = binding.etLastName.text.toString()
            navigateSerializeDataActivity()
        }

        initRecycleView()


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
        val std = Student("ali", 20, 90.5, arrayOf("math", "english", "programminig", "sientific"))
        val intent = Intent(this, SecondActivity::class.java)
        intent.putExtra("student", std)
        startActivity(intent)
    }


    private fun initRecycleView() {
        val data = ArrayList<Student>()
        data.add(Student("ali", 20, 80.1))
        data.add(Student("ahmed", 15, 70.1))
        data.add(Student("mahmoud", 22, 80.1))
        data.add(Student("salah", 30, 90.1))
        data.add(Student("sara", 25, 95.1))
        data.add(Student("nadia", 10, 80.1))
        data.add(Student("saead", 40, 40.1))
        data.add(Student("khaled", 55, 70.1))

       // val adapter = StudentAdapter(this, data)
       // binding.rvStudent.adapter = adapter

        val manager = LinearLayoutManager(this)
        manager.orientation = LinearLayoutManager.VERTICAL

        binding.rvStudent.layoutManager = manager

      /*  adapter.onItemClick = { position, student ->

            val intent = Intent(this, DetailsActivity::class.java)
            intent.putExtra("student", student)
            startActivity(intent)
            Log.e(TAG, "position: $position student: $student ")

        }*/

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