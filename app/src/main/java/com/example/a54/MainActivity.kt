package com.example.a54

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.a54.data.local.TodoJsonDataSource
import com.example.a54.data.repository.TodoRepositoryImpl
import com.example.a54.domain.usecase.GetAllTodosUseCase
import com.example.a54.domain.usecase.GetTodoUseCase
import com.example.a54.presentation.navigation.Navigation
import com.example.a54.presentation.theme.A54Theme
import com.example.a54.presentation.viewmodel.TodoViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val jsonDataSource = TodoJsonDataSource(this)
        val repository = TodoRepositoryImpl(jsonDataSource)
        val getTodosUseCase = GetAllTodosUseCase(repository)
        val toggleTodoUseCase = GetTodoUseCase(repository)
        val viewModel = TodoViewModel(getTodosUseCase, toggleTodoUseCase)
        setContent {
            A54Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Navigation(viewModel = viewModel)
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