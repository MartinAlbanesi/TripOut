package com.example.turistaapp.map.domain

import com.example.turistaapp.core.data.datastore.LocalDataStoreRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetIsMapTutorialComplete @Inject constructor(
    private val localDataStoreRepository: LocalDataStoreRepository
) {
    operator fun invoke(): Flow<Boolean?> {
        return localDataStoreRepository.getIsMapTutorialComplete()
    }
}