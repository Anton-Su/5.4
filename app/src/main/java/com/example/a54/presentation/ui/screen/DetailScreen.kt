package com.example.a54.presentation.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.a54.domain.model.TodoItem
import com.example.a54.presentation.ui.component.DetailsItemInfo
import com.example.a54.presentation.ui.component.AppTopBar
import com.example.a54.presentation.viewmodel.TodoViewModel

@Composable
fun DetailScreen(
    navHostController: NavHostController,
    viewModel: TodoViewModel,
    item: TodoItem
) {
        Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        AppTopBar(title = "Детали", viewModel = viewModel, navIcon = {
            IconButton(onClick = { navHostController.navigateUp() }) {
                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Назад")
            }
        })
        DetailsItemInfo(
            title = item.title,
            description = item.description,
            isCompleted = item.isCompleted)
            Button(onClick = {
                viewModel.deleteTodo(item)
                navHostController.popBackStack()
            },
                modifier = Modifier.padding(top = 24.dp)) {
                Text(text = "Удалить задачу")
            }
    }
}
