package com.mahmouddev.trainingproject.adapters

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.mahmouddev.trainingproject.databinding.ItemStudentBinding
import com.mahmouddev.trainingproject.roomDB.entities.Student

class StudentAdapter(var activity: Activity, var data: List<Student>) :
    RecyclerView.Adapter<StudentAdapter.MyViewHolder>() {
    val TAG = "StudentAdapter"
    var onItemClick: ((Student) -> Unit)? = null
    var onLongClick: ((Int) -> Unit)? = null

    override
    fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): MyViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
        val viewBinding = ItemStudentBinding.inflate(view, viewGroup, false)
        return MyViewHolder(viewBinding)

    }

    override fun onBindViewHolder(myViewHolder: MyViewHolder, i: Int) {
        myViewHolder.bind(data[i])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class MyViewHolder(val viewBinding: ItemStudentBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(student: Student) {
            viewBinding.apply {
                tvName.text = student.name
                tvAge.text = student.age
                tvGender.text = student.gender
                tvRate.text = student.rate.toString()
                tvGraduate.text = if (student.isGraduate) "graduated" else "student"

                card.setOnClickListener {
                    onItemClick?.invoke(data[adapterPosition])
                }
                card.setOnLongClickListener {
                    onLongClick?.invoke(data[adapterPosition].id)

                    true
                }
            }

        }
    }

}