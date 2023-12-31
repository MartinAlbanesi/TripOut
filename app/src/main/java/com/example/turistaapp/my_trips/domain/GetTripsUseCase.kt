package com.example.turistaapp.my_trips.domain // ktlint-disable package-name

import com.example.turistaapp.core.data.database.TripDBRepository
import com.example.turistaapp.create_trip.data.mapper.toTripModel
import com.example.turistaapp.create_trip.domain.models.TripModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetTripsUseCase @Inject constructor(
    private val tripDBRepository: TripDBRepository,
) {
    suspend operator fun invoke(): Flow<List<TripModel>> {
        return tripDBRepository.getAllTrips().map {
            it.map { item ->
                item.toTripModel()
            }
        }
    }
}
