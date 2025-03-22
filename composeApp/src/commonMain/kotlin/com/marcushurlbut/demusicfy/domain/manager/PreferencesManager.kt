package com.marcushurlbut.demusicfy.domain.manager

import androidx.compose.runtime.Composable
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.marcushurlbut.demusicfy.domain.dataStoreFileName
//import com.marcushurlbut.demusicfy.domain.preferencesDataStore
import com.marcushurlbut.demusicfy.domain.rememberDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

val THEME_KEY = stringPreferencesKey("theme")
val USERNAME_KEY = stringPreferencesKey("username")

class PreferencesManager() {
    private val store: DataStore<Preferences> = rememberDataStore()

    fun getDatastore(): DataStore<Preferences> {
        return this.store
    }

    fun saveTheme(theme: String) {
        runBlocking {
            store.edit { preferences ->
                preferences[THEME_KEY] = theme
            }
        }
    }

    fun getTheme(): Flow<String> {
        return store.data
            .map { preferences : Preferences ->
                preferences[THEME_KEY] ?: "Light"
            }
    }

    fun saveUsername(username: String) {
        runBlocking {
            store.edit { preferences ->
                preferences[USERNAME_KEY] = username
            }
        }
    }

    fun getUsername(): Flow<String> {
        return store.data
            .map { preferences : Preferences ->
                preferences[USERNAME_KEY] ?: "New User"
            }
    }
}