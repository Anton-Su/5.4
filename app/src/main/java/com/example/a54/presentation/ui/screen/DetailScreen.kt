package com.example.a54.presentation.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.a54.domain.model.TodoItem
import com.example.a54.presentation.ui.component.DetailsItemInfo
import com.example.a54.presentation.ui.component.TopBar

@Composable
fun DetailScreen(
    navHostController: NavHostController,
    item: TodoItem
) {
    Column(modifier = Modifier.padding(8.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        TopBar(navHostController)
        DetailsItemInfo(
            title = item.title,
            description = item.description,
            isCompleted = item.isCompleted
        )
    }
}

