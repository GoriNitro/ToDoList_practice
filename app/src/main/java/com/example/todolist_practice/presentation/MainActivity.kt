package com.example.todolist_practice.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist_practice.data.local.AppDatabase
import com.example.todolist_practice.data.local.adapter.TaskAdapter
import com.example.todolist_practice.data.local.model.TaskModel
import com.example.todolist_practice.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity(), OnClick {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        binding.rvTasks.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val dao = AppDatabase.getDatabase(this).getDao()
        val adapter = TaskAdapter(arrayListOf())
        binding.rvTasks.adapter = adapter

        lifecycleScope.launch(Dispatchers.IO) {
            val users = dao.getTasksByStatus()
            withContext(Dispatchers.Main) {
                adapter.updateTasks(users as ArrayList<TaskModel>)
            }
        }

        binding.btnSave.setOnClickListener {
            val status = binding.cbStatus.isChecked
            val title = binding.etTitle.text.toString()
            val model = TaskModel(status = status, title = title)

            lifecycleScope.launch(Dispatchers.IO) {
                dao.insertTask(model)
                withContext(Dispatchers.Main) {
                    adapter.insertTask(model)
                }
            }
        }

    }
}
