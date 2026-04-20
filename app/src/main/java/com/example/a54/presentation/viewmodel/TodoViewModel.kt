package com.example.a54.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a54.domain.model.TodoItem
import com.example.a54.domain.usecase.GetAllTodosUseCase
import com.example.a54.domain.usecase.GetTodoUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class TodoViewModel(private val getAllTodosUseCase: GetAllTodosUseCase,
                    private val toggleToDoUseCase: GetTodoUseCase): ViewModel() {
    private val _todos = MutableStateFlow<List<TodoItem>>(emptyList())
    val todos = _todos.asStateFlow()

    // new state to control coloring of completed items
    private val _highlightCompletedColor = MutableStateFlow(false)
    val highlightCompletedColor = _highlightCompletedColor.asStateFlow()

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

    fun setHighlightCompletedColor(value: Boolean) {
        _highlightCompletedColor.value = value
    }
}
