package com.example.a54.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.a54.presentation.ui.screen.DetailScreen
import com.example.a54.presentation.ui.screen.ListScreen
import com.example.a54.presentation.ui.screen.AddScreen
import com.example.a54.presentation.viewmodel.TodoViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

sealed class Screen(val route: String) {
    data object TodoListScreen : Screen("todo_list")
    data object TodoDetailScreen : Screen("todo_detail/{itemId}") {
        fun createRoute(itemId: Int) = "todo_detail/$itemId"
    }
    data object TodoAddScreen : Screen("todo_add")
}

@Composable
fun Navigation(navController: NavHostController = rememberNavController(), viewModel: TodoViewModel = viewModel()) {
    NavHost(navController, startDestination = Screen.TodoListScreen.route) {
        composable(Screen.TodoListScreen.route) {
            ListScreen(navHostController = navController, viewModel = viewModel)
        }
        composable(
            Screen.TodoDetailScreen.route,
            arguments = listOf(navArgument("itemId") { type = NavType.IntType })
        ) { backStackEntry ->
            val itemId = backStackEntry.arguments?.getInt("itemId")
            val item = viewModel.todos.collectAsState().value.find { it.id == itemId }
            if (item != null) {
                DetailScreen(navHostController = navController, viewModel = viewModel, item = item)
            }
        }
        composable(Screen.TodoAddScreen.route) {
            AddScreen(navHostController = navController, viewModel = viewModel)
        }
    }
}
