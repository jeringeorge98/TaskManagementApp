package com.assignment.taskmanagementapp.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

data class NewTaskUi(
    val title: String = "",
    val description: String = "",
    val isDone: Boolean = false,
)

class AddTaskViewModel : ViewModel() {
    var showAddDialog by mutableStateOf(false)
    private val _newTask = MutableStateFlow(NewTaskUi())
    val newTask = _newTask.asStateFlow()

    fun updateTitle(title: String) {
        _newTask.value = _newTask.value.copy(title = title)
    }

    fun updateDescription(description: String) {
        _newTask.value = _newTask.value.copy(description = description)
    }

    fun openDialog() {
        showAddDialog = true
    }

    fun closeDialog() {
        showAddDialog = false
    }
}
