package com.assignment.taskmanagementapp.ui.components

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.assignment.taskmanagementapp.domain.model.Tasks

@Suppress("ktlint:standard:function-naming")
@Composable
fun TaskItemRow(
    item: Tasks,
    modifier: Modifier = Modifier,
    onTaskDone: (Boolean) -> Unit,
    onDelete: (Tasks) -> Unit,
    onClick: (Tasks) -> Unit,
) {
    val CHECKBOX_SELECTED_SCALE = 1.2f
    val CHECKBOX_DEFAULT_SCALE = 1f
    val checkboxScale by animateFloatAsState(
        targetValue = if (item.isDone) CHECKBOX_SELECTED_SCALE else CHECKBOX_DEFAULT_SCALE,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy),
    )

    Card(
        modifier =
            modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        onClick = { onClick(item) },
    ) {
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            // Column for Text (Title and Description)
            Column(
                modifier =
                    Modifier
                        .weight(1f), // Takes up all space except for the checkbox
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = item.title,
                        style =
                            if (item.isDone) {
                                MaterialTheme.typography.titleMedium.copy(
                                    textDecoration = TextDecoration.LineThrough,
                                    color = Color.Gray,
                                )
                            } else {
                                MaterialTheme.typography.titleMedium
                            },
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    IconButton(onClick = {
                        onDelete(item)
                    }) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Delete",
                        )
                    }
                }
                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = item.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 2,
                )
            }

            Checkbox(
                checked = item.isDone,
                onCheckedChange = { onTaskDone(it) },
                modifier = Modifier.scale(checkboxScale),
            )
        }
    }
}
