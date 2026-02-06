package com.assignment.taskmanagementapp.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.assignment.taskmanagementapp.domain.model.Tasks
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

data class NewTaskUi(
    val title: String = "",
    val description: String = "",
    val isDone: Boolean = false,
    val id: String? = null,
    val timeStamp: Long? = null,
)

class AddTaskViewModel : ViewModel() {
    var showAddDialog by mutableStateOf(false)
    private val _newTask = MutableStateFlow(NewTaskUi())
    val newTask = _newTask.asStateFlow()
    var isEditMode by mutableStateOf(false)

    fun updateTitle(title: String) {
        _newTask.value = _newTask.value.copy(title = title)
    }

    fun updateDescription(description: String) {
        _newTask.value = _newTask.value.copy(description = description)
    }

    fun openDialog(tasktoEdit: Tasks? = null) {
        showAddDialog = true
        if (tasktoEdit != null) {
            isEditMode = true
            _newTask.value =
                NewTaskUi(
                    title = tasktoEdit.title,
                    description = tasktoEdit.description,
                    isDone = tasktoEdit.isDone,
                    id = tasktoEdit.id,
                    timeStamp = tasktoEdit.timeStamp,
                )
        } else {
            isEditMode = false
            _newTask.value = NewTaskUi()
        }
    }

    fun closeDialog() {
        showAddDialog = false
    }
}
