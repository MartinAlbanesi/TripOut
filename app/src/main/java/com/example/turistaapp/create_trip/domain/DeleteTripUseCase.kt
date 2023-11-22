package com.example.turistaapp.create_trip.domain

import com.example.turistaapp.core.data.database.TripDBRepository
import com.example.turistaapp.create_trip.data.mapper.toTripEntity
import com.example.turistaapp.create_trip.domain.models.TripModel
import javax.inject.Inject

class DeleteTripUseCase @Inject constructor(
    private val tripDBRepository: TripDBRepository,
) {
    suspend fun execute(trip: TripModel) {
        tripDBRepository.deleteTrip(trip.toTripEntity())
    }
}
