package com.example.a54.presentation.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.a54.domain.model.TodoItem


@Composable
fun FullCard(
    item: TodoItem,
    onClick: () -> Unit,
    onFavoriteToggle: () -> Unit,

    ) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp, horizontal = 8.dp)
            .clickable {onClick()})
    {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            CardCheckbox(
                checked = item.isCompleted,
                onChecked = { onFavoriteToggle() }
            )
            Column(
                modifier = Modifier.padding(8.dp),
            ) {
                Text(
                    text = item.title,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = item.description,
                    style = MaterialTheme.typography.bodySmall,
                )
            }
        }
    }
}

