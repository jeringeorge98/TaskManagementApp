package com.assignment.taskmanagementapp.common

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.Date

@SuppressLint("SimpleDateFormat")
fun formatDate(timestamp: Long): String {
    val sdf = SimpleDateFormat("dd/MM/yyyy")
    val netDate = Date(timestamp)
    return sdf.format(netDate)
}
