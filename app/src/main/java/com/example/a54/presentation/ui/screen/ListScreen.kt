package com.example.a54.presentation.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.a54.presentation.ui.component.FullCard
import com.example.a54.presentation.viewmodel.TodoViewModel

@Composable
fun ListScreen(
    navHostController: NavHostController,
    viewModel: TodoViewModel = viewModel()
) {
    val todos by viewModel.todos.collectAsState()
    LazyColumn(
        contentPadding = PaddingValues(top = 40.dp, start = 0.dp, end = 0.dp, bottom = 90.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(todos.size) { index ->
            val todo = todos[index]
            FullCard(
                item = todo,
                onClick = { navHostController.navigate("todo_detail/${todo.id}") },
                onFavoriteToggle = { viewModel.toggleToDo(todo.id) }
            )
        }
    }
}

