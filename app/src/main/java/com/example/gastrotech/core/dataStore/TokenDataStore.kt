package com.example.gastrotech.core.dataStore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore:DataStore<Preferences> by preferencesDataStore(name = "settings")

class TokenDataStore(private val context: Context) {
    companion object{
        private val TOKEN_KEY = stringPreferencesKey("token")
    }

    val token: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[TOKEN_KEY].toString()?:""
        }

    suspend fun save(token: String) {
        context.dataStore.edit { preference ->
            preference[TOKEN_KEY] = token
        }
    }

    suspend fun clearToken(){
        context.dataStore.edit { preference ->
            preference.remove(TOKEN_KEY)
        }
    }
}