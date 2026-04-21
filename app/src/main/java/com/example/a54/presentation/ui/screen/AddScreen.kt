package com.example.a54.presentation.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.a54.domain.model.TodoItem
import com.example.a54.presentation.ui.component.AppTopBar
import com.example.a54.presentation.viewmodel.TodoViewModel

@Composable
fun AddScreen(navHostController: NavHostController, viewModel: TodoViewModel) {
    val titleState = remember { mutableStateOf("") }
    val descState = remember { mutableStateOf("") }
    val todos = viewModel.todos.collectAsState()

    Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
        AppTopBar(title = "Новая задача", viewModel = viewModel, navIcon = {
            IconButton(onClick = { navHostController.navigateUp() }) {
                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Назад")
            }
        })

        OutlinedTextField(
            value = titleState.value,
            onValueChange = { titleState.value = it },
            label = { Text("Название задачи") },
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
        )

        OutlinedTextField(
            value = descState.value,
            onValueChange = { descState.value = it },
            label = { Text("Описание (опционально)") },
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp).height(300.dp),
            maxLines = 15,
            singleLine = false
        )

        Button(onClick = {
            val newId = (todos.value.maxOfOrNull { it.id } ?: 0) + 1
            val todo = TodoItem(
                id = newId,
                title = titleState.value,
                description = descState.value,
                isCompleted = false
            )
            viewModel.insertTodo(todo)
            navHostController.navigateUp()
        },
            modifier = Modifier.padding(top = 24.dp)) {
            Text(text = "Сохранить")
        }
    }
}
