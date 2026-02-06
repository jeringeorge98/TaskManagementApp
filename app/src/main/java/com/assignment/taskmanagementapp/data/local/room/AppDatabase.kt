package com.assignment.taskmanagementapp.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.assignment.taskmanagementapp.data.local.room.dao.TaskDao
import com.assignment.taskmanagementapp.data.local.room.entity.TaskEntity

@Database(entities = [TaskEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}
