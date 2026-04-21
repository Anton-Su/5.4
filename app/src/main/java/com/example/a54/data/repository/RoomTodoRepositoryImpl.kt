package com.example.a54.data.repository

import com.example.a54.data.local.TodoDao
import com.example.a54.data.model.TodoEntity
import com.example.a54.domain.model.TodoItem
import com.example.a54.domain.repository.TodoRepository
import kotlinx.coroutines.flow.first

class RoomTodoRepositoryImpl(private val dao: TodoDao): TodoRepository {
    override suspend fun getTodos(): List<TodoItem> {
        // collect one snapshot from flow in suspend context
        val entities = dao.getAll().first()
        return entities.map { e -> e.toDomain() }
    }

    override suspend fun toggleTodo(id: Int) {
        val entity = dao.getById(id)
        if (entity != null) {
            dao.update(entity.copy(isCompleted = !entity.isCompleted))
        }
    }

    suspend fun insert(todo: TodoItem) {
        dao.insert(TodoEntity.fromDomain(todo))
    }
}
