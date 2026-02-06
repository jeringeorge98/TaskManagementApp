package com.assignment.taskmanagementapp.data.local.room.repository

import com.assignment.taskmanagementapp.domain.model.Tasks

interface TaskRepository {
    fun addTask(task: Tasks)

    fun deleteTask(task: Tasks)

    fun updateTask(task: Tasks)

    fun getAllTasks(): List<Tasks>
}
