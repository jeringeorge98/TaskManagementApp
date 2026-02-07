package com.assignment.taskmanagementapp.common

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.Date

private val dateFormatter by lazy { SimpleDateFormat("dd/MM/yyyy") }

@SuppressLint("SimpleDateFormat")
fun formatDate(timestamp: Long): String {
    return dateFormatter.format(Date(timestamp))
}
