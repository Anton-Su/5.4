package com.example.a54.presentation.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.a54.presentation.navigation.Screen
import com.example.a54.presentation.ui.component.FullCard
import com.example.a54.presentation.ui.component.AppTopBar
import com.example.a54.presentation.viewmodel.TodoViewModel

@Composable
fun ListScreen(
    navHostController: NavHostController,
    viewModel: TodoViewModel
) {
    val todos by viewModel.todos.collectAsState()
    val highlight by viewModel.highlightCompletedColor.collectAsState()
    Column(modifier = Modifier
        .padding(start = 16.dp, end = 16.dp)
    ) {
        AppTopBar(viewModel = viewModel)
        LazyColumn(
            contentPadding = PaddingValues(top = 16.dp, start = 0.dp, end = 0.dp, bottom = 90.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp), modifier = Modifier.weight(1f)
        ) {
            items(todos.size) { index ->
                val todo = todos[index]
                FullCard(
                    item = todo,
                    onClick = { navHostController.navigate(Screen.TodoDetailScreen.createRoute(todo.id))},
                    onFavoriteToggle = { viewModel.toggleToDo(todo.id) },
                    highlightCompleted = highlight
                )
            }
        }

        Button(
            onClick = {
                navHostController.navigate(Screen.TodoAddScreen.route)
            },
            modifier = Modifier
                .navigationBarsPadding().padding(bottom = 16.dp).align(Alignment.CenterHorizontally)
        ) {
            Text("Добавить задачу")
        }
    }

}
