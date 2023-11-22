package com.example.turistaapp.map.domain

import com.example.turistaapp.core.data.datastore.LocalDataStoreRepository
import javax.inject.Inject

class SetIsMapTutorialComplete @Inject constructor(
    private val localDataStoreRepository: LocalDataStoreRepository,
) {
    suspend operator fun invoke(isComplete: Boolean) {
        localDataStoreRepository.setIsMapTutorialComplete(isComplete)
    }
}
