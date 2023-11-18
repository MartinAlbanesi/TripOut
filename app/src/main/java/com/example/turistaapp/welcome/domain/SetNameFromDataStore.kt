package com.example.turistaapp.welcome.domain

import com.example.turistaapp.core.data.datastore.LocalDataStoreRepository
import javax.inject.Inject

class SetNameFromDataStore @Inject constructor(
    private val dataStore: LocalDataStoreRepository
) {
    suspend operator fun invoke(name : String)  {
        dataStore.setName(name)
    }
}