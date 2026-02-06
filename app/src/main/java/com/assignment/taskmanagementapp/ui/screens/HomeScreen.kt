package com.assignment.taskmanagementapp.ui.screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.lifecycle.viewmodel.compose.viewModel
import com.assignment.taskmanagementapp.domain.model.Tasks
import com.assignment.taskmanagementapp.ui.components.AddTaskDialog
import com.assignment.taskmanagementapp.ui.components.TaskItemRow
import com.assignment.taskmanagementapp.ui.viewmodels.AddTaskViewModel
import com.assignment.taskmanagementapp.ui.viewmodels.HomeViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Suppress("ktlint:standard:function-naming")
@Composable
fun HomeScreen(
    modifier: Modifier,
    vm: HomeViewModel,
) {
    val addVm: AddTaskViewModel = viewModel()
    Scaffold(floatingActionButton = {
        AnimatedFab(onClick = { addVm.openDialog() }) {
            Icon(Icons.Default.Add, "Add Button")
        }
    }) { paddingValues ->
        Column(Modifier.padding(paddingValues)) {
            AllTaskList(vm, addVm)
            AddTaskDialog(
                addVm,
                onAdd = {
                    vm.addNewTask(addVm.newTask.value)
                },
                onEdit = {
                    vm.updateTask(
                        task =
                            Tasks(
                                id = addVm.newTask.value.id!!,
                                title = addVm.newTask.value.title,
                                description = addVm.newTask.value.description,
                                isDone = addVm.newTask.value.isDone,
                                timeStamp = addVm.newTask.value.timeStamp!!,
                            ),
                    )
                },
            )
        }
    }
}

// @Composable
// fun SearchBar(vm: HomeViewModel) {
//    OutlinedTextField(
//        value = vm.query.collectAsState().value,
//        onValueChange = { vm.updateSearchQuery(it) },
//        leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
//        placeholder = { Text("Search items") },
//        modifier =
//            Modifier
//                .fillMaxWidth()
//                .padding(horizontal = Dimensions.PADDING_LARGE, vertical = Dimensions.PADDING_MEDIUM),
//        singleLine = true,
//    )
// }

@Suppress("ktlint:standard:function-naming")
@Composable
fun AllTaskList(
    vm: HomeViewModel,
    addVm: AddTaskViewModel,
) {
    val tasks by vm.tasks.collectAsState()
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
    ) {
        items(tasks, key = { it.id }) { item ->
            TaskItemRow(
                item = item,
                onTaskDone = {
                    vm.toggleStatus(item)
                },
                onDelete = {
                    vm.deleteTask(item)
                },
                onClick = {
                    addVm.openDialog(item)
                },
            )
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun AnimatedFab(
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
) {
    val PRESSED_SCALE = 0.92f
    val DEFAULT_SCALE = 1f
    val PRESSED_ANIMATION_DURATION = 120L
    var pressed by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val scale by animateFloatAsState(targetValue = if (pressed) PRESSED_SCALE else DEFAULT_SCALE, animationSpec = spring())

    FloatingActionButton(
        onClick = {
            scope.launch {
                pressed = true
                delay(PRESSED_ANIMATION_DURATION)
                onClick()
                pressed = false
            }
        },
        modifier = Modifier.graphicsLayer(scaleX = scale, scaleY = scale),
    ) {
        icon()
    }
}
