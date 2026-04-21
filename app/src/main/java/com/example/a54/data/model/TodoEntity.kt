package com.example.a54.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.a54.domain.model.TodoItem

@Entity(tableName = "todos")
data class TodoEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val description: String,
    val isCompleted: Boolean
) {
    fun toDomain(): TodoItem = TodoItem(id = id, title = title, description = description, isCompleted = isCompleted)

    companion object {
        fun fromDomain(item: TodoItem): TodoEntity = TodoEntity(
            id = item.id,
            title = item.title,
            description = item.description,
            isCompleted = item.isCompleted
        )
    }
}

