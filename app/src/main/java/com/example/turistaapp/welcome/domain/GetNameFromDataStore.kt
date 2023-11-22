package com.example.turistaapp.welcome.domain

import com.example.turistaapp.core.data.datastore.LocalDataStoreRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNameFromDataStore @Inject constructor(
    private val dataStore: LocalDataStoreRepository,
) {
    operator fun invoke(): Flow<String?> {
        return dataStore.getName()
    }
}
