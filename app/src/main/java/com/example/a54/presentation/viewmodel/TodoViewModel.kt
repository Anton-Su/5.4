package com.example.a54.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a54.domain.model.TodoItem
import com.example.a54.domain.usecase.GetAllTodosUseCase
import com.example.a54.domain.usecase.GetTodoUseCase
import com.example.a54.domain.usecase.InsertTodoUseCase
import com.example.a54.domain.usecase.DeleteTodoUseCase
import com.example.a54.data.preferences.SettingsDataStore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch


class TodoViewModel(
    private val getAllTodosUseCase: GetAllTodosUseCase,
    private val toggleToDoUseCase: GetTodoUseCase,
    private val insertTodoUseCase: InsertTodoUseCase,
    private val deleteTodoUseCase: DeleteTodoUseCase,
    private val settingsDataStore: SettingsDataStore
): ViewModel() {
    private val _todos = MutableStateFlow<List<TodoItem>>(emptyList())
    val todos = _todos.asStateFlow()
    val highlightCompletedColor = settingsDataStore.highlightCompletedFlow
        .stateIn(viewModelScope, SharingStarted.Eagerly, false)

    init {
        loadTodos()
    }

    private fun loadTodos() {
        viewModelScope.launch {
            _todos.value = getAllTodosUseCase()
        }
    }

    fun toggleToDo(id: Int) {
        viewModelScope.launch {
            toggleToDoUseCase(id)
            _todos.update { currentList ->
                currentList.map { todo ->
                    if (todo.id == id) todo.copy(isCompleted = !todo.isCompleted)
                    else todo
                }
            }

        }
    }

    fun insertTodo(todo: TodoItem) {
        viewModelScope.launch {
            insertTodoUseCase(todo)
            _todos.value = getAllTodosUseCase()
        }
    }

    fun deleteTodo(todo: TodoItem) {
        viewModelScope.launch {
            deleteTodoUseCase(todo)
            _todos.value = getAllTodosUseCase()
        }
    }

    fun setHighlightCompletedColor(value: Boolean) {
        viewModelScope.launch { settingsDataStore.setHighlightCompleted(value) }
    }
}
