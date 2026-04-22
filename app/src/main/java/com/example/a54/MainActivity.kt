package com.example.a54

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.room.Room
import com.example.a54.data.local.AppDatabase
import com.example.a54.data.model.TodoEntity
import com.example.a54.data.model.TodoItemDto
import com.example.a54.data.repository.RoomTodoRepositoryImpl
import com.example.a54.domain.usecase.GetAllTodosUseCase
import com.example.a54.domain.usecase.GetTodoUseCase
import com.example.a54.navigation.Navigation
import com.example.a54.presentation.theme.A54Theme
import com.example.a54.presentation.viewmodel.TodoViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import androidx.lifecycle.lifecycleScope
import com.example.a54.data.preferences.SettingsDataStore

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "a54-db"
        ).build()
        val dao = db.todoDao()
        val repository = RoomTodoRepositoryImpl(dao)
        val settingsDataStore = SettingsDataStore(applicationContext)

        // seed database from assets if empty
        lifecycleScope.launch {
            val current = dao.getAll().first()
            if (current.isEmpty()) {
                try {
                    val json = assets.open("todos.json").bufferedReader().use { it.readText() }
                    val type = object : TypeToken<List<TodoItemDto>>() {}.type
                    val list: List<TodoItemDto> = Gson().fromJson(json, type)
                    list.forEach { dto ->
                        dao.insert(TodoEntity.fromDomain(dto.toDomain()))
                    }
                } catch (e: Exception) {
                    // ignore or log
                }
            }
        }
        val getTodosUseCase = GetAllTodosUseCase(repository)
        val toggleTodoUseCase = GetTodoUseCase(repository)
        val insertTodoUseCase = com.example.a54.domain.usecase.InsertTodoUseCase(repository)
        val deleteTodoUseCase = com.example.a54.domain.usecase.DeleteTodoUseCase(repository)
        val viewModel = TodoViewModel(getTodosUseCase, toggleTodoUseCase, insertTodoUseCase, deleteTodoUseCase, settingsDataStore)
        setContent {
            A54Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        Navigation(viewModel = viewModel)
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    androidx.compose.material3.Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    A54Theme {
        Greeting("Android")
    }
}