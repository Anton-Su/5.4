package com.example.a54.presentation.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.unit.dp

@Composable
fun DetailsItemInfo(
    title: String,
    description: String,
    isCompleted: Boolean,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.fillMaxWidth().padding(start = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineLarge
        )
        Text(
            text = description,
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = if (isCompleted) "Выполнено" else "Не выполнено",
            style = MaterialTheme.typography.bodyLarge,
            color = if (isCompleted) Color.Green else Color.Red
        )
    }
}

