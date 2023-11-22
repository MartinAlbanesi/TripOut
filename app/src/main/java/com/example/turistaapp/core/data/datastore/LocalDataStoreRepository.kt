package com.example.turistaapp.core.data.datastore

import android.app.LocaleManager
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.turistaapp.core.utils.enums.DataStoreNames
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalDataStoreRepository @Inject constructor(
    private val dataStore: DataStore<Preferences>,
    private val context: Context,
) {
    suspend fun setName(name: String) {
        dataStore.edit { pref ->
            pref[stringPreferencesKey(DataStoreNames.Name.name)] = name
        }
    }

    suspend fun setIsDarkMode(isDarkMode: Boolean) {
        dataStore.edit { pref ->
            pref[booleanPreferencesKey(DataStoreNames.IsDarkMode.name)] = isDarkMode
        }
    }

    suspend fun setIsMapTutorialComplete(isComplete: Boolean) {
        dataStore.edit { pref ->
            pref[booleanPreferencesKey(DataStoreNames.IsMapTutorialComplete.name)] = isComplete
        }
    }

    fun getName(): Flow<String?> {
        return dataStore.data.map { pref ->
            pref[stringPreferencesKey(DataStoreNames.Name.name)]
        }
    }

    fun getIsDarkMode(): Flow<Boolean?> {
        return dataStore.data.map { pref ->
            pref[booleanPreferencesKey(DataStoreNames.IsDarkMode.name)]
        }
    }

    fun getIsMapTutorialComplete(): Flow<Boolean?> {
        return dataStore.data.map { pref ->
            pref[booleanPreferencesKey(DataStoreNames.IsMapTutorialComplete.name)]
        }
    }

    fun getLanguage(): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (context.getSystemService(LocaleManager::class.java).applicationLocales.isEmpty) {
                "es"
            } else {
                context.getSystemService(LocaleManager::class.java).applicationLocales.get(0).language
            }
            // context.getSystemService(LocaleManager::class.java).applicationLocales.get(0).language
        } else {
            AppCompatDelegate.getApplicationLocales().get(0)?.language ?: "asd"
        }
    }
}
