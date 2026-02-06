package com.assignment.taskmanagementapp.domain.model

import java.util.UUID

data class Tasks(
    val id: String = UUID.randomUUID().toString(),
    val title: String,
    val description: String,
    val isDone: Boolean,
    val timeStamp: Long = System.currentTimeMillis(),
)
