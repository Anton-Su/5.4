package com.example.a54.data.preferences

import android.content.Context
import androidx.datastore.preferences.core.MutablePreferences
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "settings")

class SettingsDataStore(private val context: Context) {
    companion object {
        val HIGHLIGHT_COMPLETED = booleanPreferencesKey("highlight_completed")
    }

    val highlightCompletedFlow: Flow<Boolean> = context.dataStore.data
        .map { prefs: Preferences ->
            prefs[HIGHLIGHT_COMPLETED] ?: false
        }

    suspend fun setHighlightCompleted(value: Boolean) {
        context.dataStore.edit { prefs: MutablePreferences ->
            prefs[HIGHLIGHT_COMPLETED] = value
        }
    }
}