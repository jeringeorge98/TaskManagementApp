package com.assignment.taskmanagementapp.data.local.room.mappers

import com.assignment.taskmanagementapp.common.formatDate
import com.assignment.taskmanagementapp.data.local.room.entity.TaskEntity
import com.assignment.taskmanagementapp.domain.model.Tasks

fun Tasks.toEntity(): TaskEntity =
    TaskEntity(
        title = this.title,
        description = this.description,
        isDoneStatus = this.isDone,
        timestamp = this.timeStamp,
        id = this.id,
    )

fun TaskEntity.toDomain(): Tasks =
    Tasks(
        id = this.id,
        title = this.title,
        isDone = this.isDoneStatus,
        description = this.description,
        timeStamp = this.timestamp,
    )
