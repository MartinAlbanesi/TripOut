package com.example.turistaapp.home.domain

import com.example.turistaapp.create_trip.domain.models.LocationModel
import com.example.turistaapp.home.data.INearbySearchLocationRepository
import javax.inject.Inject

class GetNearbyLocationsUseCase @Inject constructor(
    private val nearbySearchLocationRepository: INearbySearchLocationRepository,
) {

    suspend operator fun invoke(location: String): List<LocationModel>? {
        return nearbySearchLocationRepository.getNearbyLocation(location)?.sortedBy { it.userRating }?.reversed()
    }
}
