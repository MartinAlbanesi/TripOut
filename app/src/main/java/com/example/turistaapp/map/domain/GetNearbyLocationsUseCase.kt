package com.example.turistaapp.map.domain

import com.example.turistaapp.create_trip.domain.models.LocationModel
import com.example.turistaapp.map.data.INearbySearchLocationRepository
import javax.inject.Inject

class GetNearbyLocationsUseCase @Inject constructor(
    private val nearbySearchLocationRepository: INearbySearchLocationRepository,
) {

    suspend operator fun invoke(location: String): List<LocationModel>? {
        return nearbySearchLocationRepository.getNearbyLocation(location)?.sortedBy { it.userRating }?.reversed()
    }
}
