package com.example.turistaapp.create_trip.domain // ktlint-disable package-name

import com.example.turistaapp.create_trip.data.database.repository.TripDBRepository
import com.example.turistaapp.create_trip.data.mapper.toTripModel
import com.example.turistaapp.create_trip.domain.models.TripModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetTripsUseCase @Inject constructor(
    private val tripDBRepository: TripDBRepository,
) {
    suspend fun execute(): Flow<List<TripModel>> {
        var trips: List<TripModel> = emptyList()

        val flowTrips = flow {
            tripDBRepository.getAllTrips().collect {
                trips = it.map { tripEntity ->
                    tripEntity.toTripModel()
                }
                emit(trips)
            }
        }

        return flowTrips
    }
}
