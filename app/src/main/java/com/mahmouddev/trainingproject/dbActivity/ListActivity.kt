package com.mahmouddev.trainingproject.dbActivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mahmouddev.trainingproject.R
import com.mahmouddev.trainingproject.adapters.StudentAdapter
import com.mahmouddev.trainingproject.databinding.ActivityAddBinding
import com.mahmouddev.trainingproject.databinding.ActivityListBinding
import com.mahmouddev.trainingproject.roomDB.AppDatabase
import com.mahmouddev.trainingproject.roomDB.DatabaseImp
import com.mahmouddev.trainingproject.roomDB.dbUtil.Status
import com.mahmouddev.trainingproject.roomDB.dbUtil.ViewModelFactory
import com.mahmouddev.trainingproject.roomDB.entities.Student
import com.mahmouddev.trainingproject.viewmodel.StudentViewModel

class ListActivity : AppCompatActivity() {
    val TAG = "ListActivity"

    lateinit var binding: ActivityListBinding
    lateinit var viewModel: StudentViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViewModel()
        getStudentAsLiveData()
        deleteAsLiveData()
        binding.fab.setOnClickListener {
            startActivity(Intent(this, AddActivity::class.java))
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.fetchStudents()
    }

    private fun initRecycleView(data: List<Student>) {
        val stdAdapter = StudentAdapter(this, data)

        binding.rv.apply {
            layoutManager = LinearLayoutManager(context).apply {
                orientation = LinearLayoutManager.VERTICAL
            }
            adapter = stdAdapter
        }

        stdAdapter.onItemClick = {
            val intent = Intent(this, EditActivity::class.java)
            intent.putExtra("student", it)
            startActivity(intent)
        }

        stdAdapter.onLongClick = { id ->
            Log.e(TAG, "onLongClick: $id", )
            viewModel.deleteStudent(id)

        }
    }

    private fun initViewModel() {
        val database = AppDatabase.getInstance(this)
        val dbHelper = DatabaseImp(database.daoStudent())
        viewModel =
            ViewModelProvider(this, ViewModelFactory(dbHelper,null)).get(StudentViewModel::class.java)
    }

    private fun getStudentAsLiveData() {
        viewModel.getStudentsAsLiveData().observe(this, {
            when (it.status) {
                Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                }

                Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    if (!it.data.isNullOrEmpty()) {
                        initRecycleView(it.data)
                    } else {
                        initRecycleView(ArrayList())
                        Toast.makeText(this, "Empty data!", Toast.LENGTH_SHORT).show()
                    }

                }

                Status.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this, "${it.message}", Toast.LENGTH_SHORT).show()

                }
            }

        })
    }

    private fun deleteAsLiveData() {
        viewModel.deleteAsLiveData().observe(this, {
            when (it.status) {
                Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                }

                Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    viewModel.fetchStudents()
                }

                Status.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this, "${it.message}", Toast.LENGTH_SHORT).show()

                }
            }

        })
    }
}