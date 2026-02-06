package com.assignment.taskmanagementapp.data.local.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.assignment.taskmanagementapp.data.local.room.entity.TaskEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Query("SELECT * FROM tasks ORDER BY timestamp DESC")
    fun getAllTasks(): Flow<List<TaskEntity>>

    @Query("SELECT * FROM tasks WHERE id = :id")
    fun getById(id: Long): Flow<TaskEntity>?

    @Upsert
    suspend fun upsert(task: TaskEntity): Long

    @Delete
    suspend fun delete(task: TaskEntity)
}
