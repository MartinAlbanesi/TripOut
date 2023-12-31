package com.example.turistaapp.create_trip.domain // ktlint-disable package-name

import com.example.turistaapp.core.data.database.TripDBRepository
import com.example.turistaapp.core.utils.GsonConverter
import com.example.turistaapp.create_trip.domain.models.LocationModel
import javax.inject.Inject

class GetDestinationLocationsFromDataBase @Inject constructor(
    private val tripDBRepository: TripDBRepository,
) {
    suspend operator fun invoke(): List<LocationModel> {
        val tripsLocations = tripDBRepository.getLocationsFromDestination()
        if (tripsLocations.isEmpty()) {
            return emptyList()
        }
        return tripsLocations.map {
            GsonConverter.fromJson(it, LocationModel::class.java)
        }
    }
}
