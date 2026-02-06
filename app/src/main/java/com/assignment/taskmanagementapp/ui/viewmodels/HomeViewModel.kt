package com.assignment.taskmanagementapp.ui.viewmodels

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assignment.taskmanagementapp.data.local.room.repository.TaskRepository
import com.assignment.taskmanagementapp.domain.model.Tasks
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HomeViewModel(
    private val taskRepository: TaskRepository,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private companion object {
        const val KEY_SEARCH_QUERY = "search_query"
    }

    private val _tasks = taskRepository.getAllTasks()
    val tasks = _tasks.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
    val query = savedStateHandle.getStateFlow(KEY_SEARCH_QUERY, "")

    val filteredItems =
        combine(query, _tasks) { queryString, combinedItems ->
            combinedItems.filter { it.title.contains(queryString, ignoreCase = true) }
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    fun updateSearchQuery(newQuery: String) {
        savedStateHandle[KEY_SEARCH_QUERY] = newQuery
    }

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
        val updatedTask = task.copy(isDone = !task.isDone)
        viewModelScope.launch {
            taskRepository.upsertTask(updatedTask)
        }
    }
}
