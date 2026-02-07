package com.assignment.taskmanagementapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import com.assignment.taskmanagementapp.data.local.room.repository.TaskRepository

/**
 * To create the HomeViewModel with the repository depndency
 *
 *
 */
class HomeViewModelFactory(
    private val taskRepository: TaskRepository,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(
        modelClass: Class<T>,
        extras: CreationExtras,
    ): T {
        val handle = extras.createSavedStateHandle()
        return HomeViewModel(
            taskRepository = taskRepository,
            savedStateHandle = handle,
        ) as T
    }
}
