package com.example.todolist_practice.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks_table")
data class TaskModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val title: String,
    var status: Boolean
)