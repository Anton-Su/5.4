package com.example.a54.domain.usecase

import com.example.a54.domain.model.TodoItem
import com.example.a54.domain.repository.TodoRepository

class DeleteTodoUseCase(private val repository: TodoRepository) {
    suspend operator fun invoke(todo: TodoItem) {
        repository.deleteTodo(todo)
    }
}

