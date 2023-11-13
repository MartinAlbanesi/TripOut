package com.example.turistaapp.core.data.datastore

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
    private val dataStore : DataStore<Preferences>
) {
    suspend fun setName(name : String){
        dataStore.edit {pref ->
            pref[stringPreferencesKey(DataStoreNames.Name.name)] = name
        }
    }

    suspend fun setIsDarkMode(isDarkMode: Boolean){
        dataStore.edit {pref ->
            pref[booleanPreferencesKey(DataStoreNames.IsDarkMode.name)] = isDarkMode
        }
    }

    suspend fun setIsMapTutorialComplete(isComplete: Boolean){
        dataStore.edit {pref ->
            pref[booleanPreferencesKey(DataStoreNames.IsMapTutorialComplete.name)] = isComplete
        }
    }

    suspend fun setIsShakeGameTutorialComplete(isComplete: Boolean){
        dataStore.edit {pref ->
            pref[booleanPreferencesKey(DataStoreNames.IsShakeGameTutorialComplete.name)] = isComplete
        }
    }

    fun getName() : Flow<String?>{
        return dataStore.data.map { pref ->
            pref[stringPreferencesKey(DataStoreNames.Name.name)]
        }
    }

    fun getIsDarkMode() : Flow<Boolean?>{
        return dataStore.data.map { pref ->
            pref[booleanPreferencesKey(DataStoreNames.IsDarkMode.name)]
        }
    }

    fun getIsMapTutorialComplete() : Flow<Boolean?>{
        return dataStore.data.map { pref ->
            pref[booleanPreferencesKey(DataStoreNames.IsMapTutorialComplete.name)]
        }
    }

    fun getIsShakeGameTutorialComplete() : Flow<Boolean?>{
        return dataStore.data.map { pref ->
            pref[booleanPreferencesKey(DataStoreNames.IsShakeGameTutorialComplete.name)]
        }
    }

}