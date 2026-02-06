package com.assignment.taskmanagementapp

import android.app.Application
import com.assignment.taskmanagementapp.data.local.room.AppDatabaseProvider
import com.assignment.taskmanagementapp.data.local.room.repository.TaskRepositoryImpl

class TaskManagementApplication : Application() {
    private val database by lazy { AppDatabaseProvider.getDatabase(this) }
    val taskRepository by lazy {
        TaskRepositoryImpl(
            database.taskDao(),
        )
    }
}
