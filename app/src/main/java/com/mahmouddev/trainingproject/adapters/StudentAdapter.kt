package com.mahmouddev.trainingproject.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mahmouddev.trainingproject.Student
import com.mahmouddev.trainingproject.databinding.ItemStudentBinding

class StudentAdapter(val data: ArrayList<Student>) :
    RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemStudentBinding.inflate(inflater, parent, false)
        return StudentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {


        holder.bind(data.get(position))

    }

    override fun getItemCount(): Int {
        return data.size
    }


    inner class StudentViewHolder(var binding: ItemStudentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(std: Student) {
            binding.tvName.text = std.name
            binding.tvAge.text = std.age.toString()
            binding.tvRate.text = std.rate.toString()

        }


    }
}