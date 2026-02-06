package com.assignment.taskmanagementapp.ui.screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Suppress("ktlint:standard:function-naming")
@Composable
fun HomeScreen(modifier: Modifier) {
    Scaffold(floatingActionButton = {
        AnimatedFab(onClick = { }) {
            Icon(Icons.Default.Add, "Add Button")
        }
    }) { paddingValues ->
        Column(Modifier.padding(paddingValues)) {
            Text("Hello world!")
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
