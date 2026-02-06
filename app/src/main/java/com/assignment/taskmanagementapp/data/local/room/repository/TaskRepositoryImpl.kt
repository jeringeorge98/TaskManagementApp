package com.assignment.taskmanagementapp.data.local.room.repository

import com.assignment.taskmanagementapp.data.local.room.dao.TaskDao
import com.assignment.taskmanagementapp.data.local.room.mappers.toDomain
import com.assignment.taskmanagementapp.data.local.room.mappers.toEntity
import com.assignment.taskmanagementapp.domain.model.Tasks
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TaskRepositoryImpl(
    private val taskDao: TaskDao,
) : TaskRepository {
    override suspend fun deleteTask(task: Tasks) {
        taskDao.delete(task.toEntity())
    }

    override suspend fun upsertTask(task: Tasks) {
        taskDao.upsert(task.toEntity())
    }

    override fun getAllTasks(): Flow<List<Tasks>> = taskDao.getAllTasks().map { it -> it.map { itemEntity -> itemEntity.toDomain() } }
}
