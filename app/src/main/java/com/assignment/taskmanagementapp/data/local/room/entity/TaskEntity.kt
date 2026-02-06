package com.assignment.taskmanagementapp.data.local.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    val timestamp: Long,
    val title: String,
    val description: String,
    val isDoneStatus: Boolean,
)
