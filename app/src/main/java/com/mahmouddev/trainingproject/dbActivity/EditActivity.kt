package com.mahmouddev.trainingproject.dbActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.mahmouddev.trainingproject.R
import com.mahmouddev.trainingproject.databinding.ActivityEditBinding
import com.mahmouddev.trainingproject.roomDB.AppDatabase
import com.mahmouddev.trainingproject.roomDB.DatabaseImp
import com.mahmouddev.trainingproject.roomDB.dbUtil.Status
import com.mahmouddev.trainingproject.roomDB.dbUtil.ViewModelFactory
import com.mahmouddev.trainingproject.roomDB.entities.Student
import com.mahmouddev.trainingproject.util.Gender
import com.mahmouddev.trainingproject.viewmodel.StudentViewModel

class EditActivity : AppCompatActivity() {
    val TAG = "EditActivity"
    lateinit var binding: ActivityEditBinding
    lateinit var viewModel: StudentViewModel

    var age = ""
    var isGraduate = false
    var gender = ""
    var spinnerArray = arrayOf(
        "18",
        "19",
        "20",
        "21",
        "22",
        "23",
        "24",
        "25",
        "26",
        "27",
        "28",
        "29",
        "30",
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val student = intent.extras?.getParcelable<Student>("student")
        Log.e(TAG, "onCreate: ${student?.id}", )
        initViewModel()
        handleSpinner()
        handleCheckBox()
        handleRadioButton()
        updateAsLiveData()
        binding.apply {
            etName.setText(student?.name)
            etRate.setText(student?.rate.toString())
            cbGraduate.isChecked = student!!.isGraduate

            if (student.gender == Gender.MALE.toString()) {
                rbMale.isChecked = true
            } else {
                rbFemale.isChecked = true
            }

            val adapter = spAge.adapter as ArrayAdapter<String>
            val position = adapter.getPosition(student.age)
            spAge.setSelection(position)

            btnSave.setOnClickListener {
                val name = etName.text.toString()
                val rate = etRate.text.toString()
                viewModel.updateStudent(Student(name, age, rate.toInt(), isGraduate, gender,student.id))
            }
        }

    }

    private fun handleSpinner() {
        val adapter =
            ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, spinnerArray)
        binding.spAge.adapter = adapter

        binding.spAge.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parentView: AdapterView<*>?,
                selectedItemView: View?,
                position: Int,
                id: Long
            ) {
                val selectedItem = parentView?.getItemAtPosition(position).toString()
                Log.e(TAG, "onItemSelected: $selectedItem")
                age = selectedItem
            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {
                // your code here
            }

        }
    }

    private fun handleCheckBox() {

        binding.cbGraduate.setOnCheckedChangeListener { buttonView, isChecked ->
            Log.e(TAG, "handleCheckBox: $isChecked")

            isGraduate = isChecked
        }
        //  binding.cbGraduate.isChecked

    }

    private fun handleRadioButton() {
        binding.rg.setOnCheckedChangeListener { group, checkId ->
            when (checkId) {
                R.id.rbMale -> {
                    gender = Gender.MALE.toString()

                }
                R.id.rbFemale -> {
                    gender = Gender.FEMALE.toString()

                }
            }

        }
    }

    private fun initViewModel() {
        val database = AppDatabase.getInstance(this)
        val dbHelper = DatabaseImp(database.daoStudent())
        viewModel =
            ViewModelProvider(this, ViewModelFactory(dbHelper,null)).get(StudentViewModel::class.java)
    }


    private fun updateAsLiveData() {
        viewModel.updateAsLiveData().observe(this, {
            when (it.status) {
                Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                Status.SUCCESS -> {
                    finish()
                    binding.progressBar.visibility = View.GONE

                }
                Status.ERROR -> {
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                    binding.progressBar.visibility = View.GONE
                }
            }

        })
    }


}