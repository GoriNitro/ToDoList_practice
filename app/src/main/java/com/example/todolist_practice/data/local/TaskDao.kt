package com.example.todolist_practice.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.todolist_practice.data.local.model.TaskModel

@Dao
interface TaskDao {

    @Query("SELECT * FROM tasks_table ORDER BY status ASC")
    fun getTasksByStatus(): List<TaskModel>

    @Insert
    fun insertTask(model: TaskModel)

    @Delete
    fun deleteTask(model: TaskModel)

    @Update
    fun updateTask(model: TaskModel)
}