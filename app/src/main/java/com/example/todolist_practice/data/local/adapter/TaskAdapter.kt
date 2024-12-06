package com.example.todolist_practice.data.local.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist_practice.R
import com.example.todolist_practice.data.local.model.TaskModel
import com.example.todolist_practice.databinding.ItemTaskBinding

class TaskAdapter(private val list: ArrayList<TaskModel>) :
    RecyclerView.Adapter<TaskAdapter.ViewHolder>() {

    fun insertTask(taskModel: TaskModel) {
        list.add(taskModel)
        list.sortBy { it.status }
        notifyItemInserted(list.size - 1)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateTasks(newList: ArrayList<TaskModel>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemTaskBinding.bind(view)
        fun bind(model: TaskModel) {
            binding.tvName.isChecked = model.status
            binding.tvYear.text = model.title
        }
    }
}