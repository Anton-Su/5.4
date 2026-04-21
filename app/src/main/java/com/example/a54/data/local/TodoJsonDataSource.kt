package com.example.a54.data.local

import android.content.Context
import com.example.a54.data.model.TodoItemDto
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class TodoJsonDataSource(private val context: Context) {
    private val gson = Gson()
    fun getTodosFromJson(): List<TodoItemDto> {
        val json = context.assets.open("todos.json").bufferedReader().use { it.readText() }
        val type = object : TypeToken<List<TodoItemDto>>() {}.type
        return gson.fromJson(json, type)
    }



}

