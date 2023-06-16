package com.examples.mycapstoneproject.data.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserPreferences  private constructor(private val dataStore: DataStore<Preferences>) {
    private val TOKEN = stringPreferencesKey("token")
    private val EMAIL = stringPreferencesKey("email")
    private val NAME = stringPreferencesKey("name")

    fun getUser(): Flow<String> {
        return dataStore.data.map { preferences ->
            preferences[TOKEN] ?: ""
        }
    }

    suspend fun saveUser(token: String) {
        dataStore.edit { preferences ->
            preferences[TOKEN] = token
        }
    }
    suspend fun deleteUser(){
        dataStore.edit { preferences ->
            preferences[TOKEN] = ""
        }
    }
    fun getEmail(): Flow<String> {
        return dataStore.data.map { preferences ->
            preferences[EMAIL] ?: ""
        }
    }

    suspend fun saveEmail(email: String) {
        dataStore.edit { preferences ->
            preferences[EMAIL] = email
        }
    }
    suspend fun deleteEmail(){
        dataStore.edit { preferences ->
            preferences[EMAIL] = ""
        }
    }
    fun getName(): Flow<String> {
        return dataStore.data.map { preferences ->
            preferences[NAME] ?: ""
        }
    }

    suspend fun saveName(password: String) {
        dataStore.edit { preferences ->
            preferences[NAME] = password
        }
    }
    suspend fun deleteName(){
        dataStore.edit { preferences ->
            preferences[NAME] = ""
        }
    }


    companion object {
        @Volatile
        private var INSTANCE: UserPreferences? = null

        fun getInstance(dataStore: DataStore<Preferences>): UserPreferences {
            return INSTANCE ?: synchronized(this) {
                val instance = UserPreferences(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }
}