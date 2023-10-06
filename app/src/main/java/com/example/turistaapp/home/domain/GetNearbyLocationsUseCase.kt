package com.example.turistaapp.home.domain

import com.example.turistaapp.home.data.INearbySearchLocationRepository
import com.example.turistaapp.home.domain.models.NearbyLocation
import javax.inject.Inject

class GetNearbyLocationsUseCase @Inject constructor(
    private val nearbySearchLocationRepository: INearbySearchLocationRepository
) {

    suspend operator fun invoke(location: String): List<NearbyLocation> {
        return nearbySearchLocationRepository.getNearbyLocation(location)
    }
}