package com.example.turistaapp.create_trip.domain // ktlint-disable package-name

import com.example.turistaapp.core.data.database.TripDBRepository
import com.example.turistaapp.create_trip.data.mapper.toTripEntity
import com.example.turistaapp.create_trip.domain.models.TripModel
import javax.inject.Inject

class InsertTripUseCase @Inject constructor(
    private val tripDBRepository: TripDBRepository,
) {
    suspend fun execute(trip: TripModel) {
        val tripEntity = trip.toTripEntity()
        tripDBRepository.insertTrip(tripEntity)
    }
}
