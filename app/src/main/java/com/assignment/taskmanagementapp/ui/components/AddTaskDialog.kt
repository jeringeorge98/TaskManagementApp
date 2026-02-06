package com.assignment.taskmanagementapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.ExposedDropdownMenuDefaults.TrailingIcon
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.assignment.taskmanagementapp.ui.viewmodels.AddTaskViewModel

@Suppress("ktlint:standard:function-naming")
@Composable
fun AddTaskDialog(
    vm: AddTaskViewModel,
    onAdd: () -> Unit,
) {
    val PADDING_MEDIUM = 12.dp
    val uiState by vm.newTask.collectAsState()
    var titleTapped by remember { mutableStateOf(false) }
    val titleError = titleTapped && uiState.title.isBlank()
    var descriptionTapped by remember { mutableStateOf(false) }
    val descriptionError = descriptionTapped && uiState.description.isBlank()
    if (!vm.showAddDialog) {
        return
    }

    AlertDialog(
        onDismissRequest = { vm.closeDialog() },
        confirmButton = {
            TextButton(
                onClick = {
                    onAdd()
                    vm.closeDialog()
                },
            ) { Text("Add") }
        },
        dismissButton = {
            TextButton(onClick = { vm.closeDialog() }) { Text("Cancel") }
        },
        title = {
            Text("Add Task")
        },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(PADDING_MEDIUM)) {
                OutlinedTextField(
                    value = uiState.title,
                    onValueChange = {
                        titleTapped = true
                        vm.updateTitle(it)
                    },
                    label = { Text("Task Title") },
                    isError = titleError,
                    trailingIcon = {
                        if (titleError) Icon(Icons.Default.Warning, contentDescription = null)
                    },
                    supportingText = {
                        if (titleError) Text("Please enter a title")
                    },
                    singleLine = true,
                )
                OutlinedTextField(
                    value = uiState.description,
                    onValueChange = {
                        descriptionTapped = true
                        vm.updateDescription(it)
                    },
                    isError = descriptionError,
                    trailingIcon = {
                        if (titleError) Icon(Icons.Default.Warning, contentDescription = null)
                    },
                    supportingText = {
                        if (titleError) Text("Please enter a description")
                    },
                    label = { Text("Task Description") },
                )
            }
        },
    )
}
