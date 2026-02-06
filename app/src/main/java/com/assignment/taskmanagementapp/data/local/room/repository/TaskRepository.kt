package com.assignment.taskmanagementapp.data.local.room.repository

import com.assignment.taskmanagementapp.domain.model.Tasks
import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    suspend fun deleteTask(task: Tasks)

    suspend fun upsertTask(task: Tasks)

    fun getAllTasks(): Flow<List<Tasks>>
}
