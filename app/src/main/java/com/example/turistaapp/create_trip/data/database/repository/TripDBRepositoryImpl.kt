package com.example.turistaapp.create_trip.data.database.repository

import com.example.turistaapp.create_trip.data.database.entities.TripEntity
import kotlinx.coroutines.flow.Flow

class TripDBRepositoryImpl : TripDBRepository {
    override suspend fun insertTrip(trip: TripEntity) {
        TODO()
    }

    override suspend fun getAllTrips(): Flow<List<TripEntity>> {
        TODO()
    }
}