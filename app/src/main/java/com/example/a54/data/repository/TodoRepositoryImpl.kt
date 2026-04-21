package com.example.a54.data.repository

import com.example.a54.data.local.TodoJsonDataSource
import com.example.a54.domain.model.TodoItem
import com.example.a54.domain.repository.TodoRepository

class TodoRepositoryImpl(dataSource: TodoJsonDataSource) : TodoRepository {
    private val todosDto = dataSource.getTodosFromJson().toMutableList()

    override suspend fun getTodos(): List<TodoItem> {
        return todosDto.map { it.toDomain() }
    }

    override suspend fun toggleTodo(id: Int) {
        val index = todosDto.indexOfFirst { it.id == id }
        val old = todosDto[index]
        val updated = old.copy(isCompleted = !old.isCompleted)
        todosDto[index] = updated
    }

    override suspend fun insertTodo(todo: TodoItem) {
        val newId = (todosDto.maxOfOrNull { it.id } ?: 0) + 1
        val newTodoDto = com.example.a54.data.model.TodoItemDto(
            id = newId,
            title = todo.title,
            description = todo.description,
            isCompleted = todo.isCompleted
        )
        todosDto.add(newTodoDto)
    }

    override suspend fun deleteTodo(todo: TodoItem) {
        val index = todosDto.indexOfFirst { it.id == todo.id }
        if (index != -1) {
            todosDto.removeAt(index)
        }
    }
}
