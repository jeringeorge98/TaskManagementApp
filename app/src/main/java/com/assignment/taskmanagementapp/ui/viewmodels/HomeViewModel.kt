package com.assignment.taskmanagementapp.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assignment.taskmanagementapp.data.local.room.repository.TaskRepository
import com.assignment.taskmanagementapp.domain.model.Tasks
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HomeViewModel(
    private val taskRepository: TaskRepository,
) : ViewModel() {
    private val _tasks = taskRepository.getAllTasks()
    val tasks = _tasks.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    fun addNewTask(task: NewTaskUi) {
        if (task.title.isBlank()) return
        val task = Tasks(title = task.title, description = task.description, isDone = task.isDone)
        viewModelScope.launch {
            taskRepository.upsertTask(task)
        }
    }

    fun updateTask(task: Tasks) {
        if (task.title.isBlank()) return
        viewModelScope.launch {
            taskRepository.upsertTask(task)
        }
    }

    fun deleteTask(task: Tasks) {
        viewModelScope.launch {
            taskRepository.deleteTask(task)
        }
    }

    fun toggleStatus(task: Tasks) {
        Log.d("before update", task.toString())
        val updatedTask = task.copy(isDone = !task.isDone)
        Log.d("after copy", updatedTask.toString())

        viewModelScope.launch {
            taskRepository.upsertTask(updatedTask)
        }
    }
}
