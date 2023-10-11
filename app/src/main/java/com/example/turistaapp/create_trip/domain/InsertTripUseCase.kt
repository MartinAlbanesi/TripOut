package com.example.turistaapp.create_trip.domain

import com.example.turistaapp.create_trip.data.database.entities.TripEntity
import com.example.turistaapp.create_trip.data.database.repository.TripDBRepository
import com.example.turistaapp.create_trip.data.mapper.toTripEntity
import com.example.turistaapp.create_trip.domain.models.TripModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class InsertTripUseCase @Inject constructor(
    private val tripDBRepository: TripDBRepository
) {
    suspend fun execute(trip: TripModel) {

        val tripEntity = trip.toTripEntity()

        if (tripEntity != null) {
            tripDBRepository.insertTrip(tripEntity)
        }
    }

}