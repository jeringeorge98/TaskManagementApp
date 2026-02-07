package com.assignment.taskmanagementapp.ui.viewmodels

import androidx.lifecycle.SavedStateHandle
import com.assignment.taskmanagementapp.data.local.room.repository.TaskRepository
import com.assignment.taskmanagementapp.domain.model.Tasks
import io.mockk.MockKAnnotations
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.just
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

class HomeViewModelTest {
    private lateinit var viewModel: HomeViewModel
    private val testDispatcher = StandardTestDispatcher()
    private val testScope = TestScope(testDispatcher)
    private lateinit var savedStateHandle: SavedStateHandle

    @MockK
    private lateinit var taskRepository: TaskRepository

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(testDispatcher)

        savedStateHandle = SavedStateHandle()
        // Mock repository behavior
        coEvery { taskRepository.getAllTasks() } returns flowOf(emptyList())
        coEvery { taskRepository.upsertTask(any()) } just Runs
        coEvery { taskRepository.deleteTask(any()) } just Runs

        viewModel =
            HomeViewModel(
                taskRepository = taskRepository,
                savedStateHandle = savedStateHandle,
            )
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `given new task when addNewTask then task added to repository`() {
        // Given
        val newTask =
            NewTaskUi(
                title = "Test Task",
                description = "Test Description",
                isDone = false,
            )

        // When
        viewModel.addNewTask(newTask)
        testScope.advanceUntilIdle()

        // Then
        coVerify(exactly = 1) { taskRepository.upsertTask(any()) }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `given existing task when toggleStatus then upsertTask is called with flipped status`() {
        // Given
        val task =
            Tasks(
                title = "Toggle Me",
                description = "Desc",
                isDone = false,
            )
        val id = task.id
        // When
        viewModel.toggleStatus(task)
        testScope.advanceUntilIdle()

        // Then
        // We verify that the task sent to the repository has isDone = true
        coVerify(exactly = 1) {
            taskRepository.upsertTask(match { it.id == id && it.isDone })
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `given existing task when deleteTask then repository deleteTask is called`() {
        // Given
        val task =
            Tasks(
                title = "Delete Me",
                description = "Desc",
                isDone = false,
            )
        val id = task.id
        // When
        viewModel.deleteTask(task)
        testScope.advanceUntilIdle()

        // Then
        coVerify(exactly = 1) {
            taskRepository.deleteTask(match { it.id == id })
        }
    }

    @Test
    fun `given new query when updateSearchQuery then query flow emits new value`() {
        // Given
        val newSearchString = "Laundry"

        // When
        viewModel.updateSearchQuery(newSearchString)

        // Then
        // savedStateHandle[KEY_SEARCH_QUERY] should be updated
        assertEquals(newSearchString, viewModel.query.value)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `given task with blank title when updateTask then repository is not called`() {
        // Given
        val invalidTask = Tasks(title = "", description = "", isDone = false)

        // When
        viewModel.updateTask(invalidTask)
        testScope.advanceUntilIdle()

        // Then
        coVerify(exactly = 0) { taskRepository.upsertTask(any()) }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `given valid task when updateTask then repository upsertTask is called`() {
        // Given
        val taskToUpdate =
            Tasks(
                title = "Fix Bug",
                description = "High priority",
                isDone = true,
            )

        // When
        viewModel.updateTask(taskToUpdate)
        testScope.advanceUntilIdle()

        // Then
        coVerify(exactly = 1) { taskRepository.upsertTask(taskToUpdate) }
    }
}
