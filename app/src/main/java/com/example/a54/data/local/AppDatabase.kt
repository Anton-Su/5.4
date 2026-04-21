package com.example.a54.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.a54.data.model.TodoEntity

@Database(entities = [TodoEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun todoDao(): TodoDao
}
