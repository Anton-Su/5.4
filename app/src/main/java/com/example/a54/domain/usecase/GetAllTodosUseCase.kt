package com.example.a54.domain.usecase

import com.example.a54.domain.model.TodoItem
import com.example.a54.domain.repository.TodoRepository

class GetAllTodosUseCase(private val repository: TodoRepository) {
    suspend operator fun invoke(): List<TodoItem> {
        return repository.getTodos()
    }
}

