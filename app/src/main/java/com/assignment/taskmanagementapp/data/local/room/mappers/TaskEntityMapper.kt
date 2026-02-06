package com.assignment.taskmanagementapp.data.local.room.mappers

import com.assignment.taskmanagementapp.common.formatDate
import com.assignment.taskmanagementapp.data.local.room.entity.TaskEntity
import com.assignment.taskmanagementapp.domain.model.Status
import com.assignment.taskmanagementapp.domain.model.Tasks

fun Tasks.toEntity(): TaskEntity =
    TaskEntity(
        title = this.title,
        description = this.description,
        isDoneStatus = this.isDone,
        timestamp = System.currentTimeMillis(),
    )

fun TaskEntity.toDomain(): Tasks =
    Tasks(
        title = this.title,
        isDone = this.isDoneStatus,
        description = this.description,
        date = formatDate(this.timestamp),
    )
