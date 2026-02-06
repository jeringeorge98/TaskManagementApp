package com.assignment.taskmanagementapp.domain.model

data class Tasks(
    val title: String,
    val description: String,
    val isDone: Boolean,
    val date: String,
)
