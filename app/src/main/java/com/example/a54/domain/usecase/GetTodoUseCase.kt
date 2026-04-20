package com.example.a54.domain.usecase

import com.example.a54.domain.repository.TodoRepository

class GetTodoUseCase(private val repository: TodoRepository) {
    suspend operator fun invoke(id: Int) {
        return repository.toggleTodo(id)
    }
}

