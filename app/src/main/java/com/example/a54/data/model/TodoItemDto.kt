package com.example.a54.data.model

import com.example.a54.domain.model.TodoItem

data class TodoItemDto(
    val id: Int,
    val title: String,
    val description: String,
    val isCompleted: Boolean
) {
    fun toDomain(): TodoItem {
        return TodoItem(
            id = id,
            title = title,
            description = description,
            isCompleted = isCompleted
        )
    }
}

