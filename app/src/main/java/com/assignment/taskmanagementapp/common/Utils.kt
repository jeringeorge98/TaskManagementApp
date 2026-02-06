package com.assignment.taskmanagementapp.common

import java.text.SimpleDateFormat
import java.util.Date

fun formatDate(timestamp: Long): String {
    val sdf = SimpleDateFormat("dd/MM/yyyy")
    val netDate = Date(timestamp)
    return sdf.format(netDate)
}
