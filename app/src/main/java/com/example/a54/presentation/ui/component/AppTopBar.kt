package com.example.a54.presentation.ui.component

import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.a54.presentation.viewmodel.TodoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    modifier: Modifier = Modifier,
    title: String = "Todo List",
    viewModel: TodoViewModel = viewModel(),
    showSwitch: Boolean = true,
    navIcon: @Composable () -> Unit = {}
) {
    val highlight by viewModel.highlightCompletedColor.collectAsState()

    CenterAlignedTopAppBar(
        title = { Text(text = title) },
        modifier = modifier.statusBarsPadding(),
        navigationIcon = navIcon,
        actions = {
            if (showSwitch) {
                Text(text = "Цвет")
                Switch(checked = highlight, onCheckedChange = { viewModel.setHighlightCompletedColor(it) })
            }
        }
    )
}
