package com.mahmouddev.trainingproject.adapters

import android.app.Activity
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mahmouddev.trainingproject.DetailsActivity
import com.mahmouddev.trainingproject.Student
import com.mahmouddev.trainingproject.databinding.ItemStudentBinding

class StudentAdapter(var activity: Activity,val data: ArrayList<Student>) :
    RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {
    val TAG = "StudentAdapter"
    lateinit var onItemClick: ((Int,Student) -> Unit)


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
            binding.tvRate.text = "${std.rate}"

            binding.linContainer.setOnClickListener {
                onItemClick.invoke(adapterPosition,data.get(adapterPosition))
             //   Log.e(TAG, "adapterPosition: $adapterPosition", )
//                val intent = Intent(activity,DetailsActivity::class.java)
//                activity.startActivity(intent)

            }

        }


    }
}