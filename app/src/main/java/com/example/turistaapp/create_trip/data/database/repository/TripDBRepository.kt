package com.example.turistaapp.create_trip.data.database.repository

import com.example.turistaapp.create_trip.data.database.entities.TripEntity
import kotlinx.coroutines.flow.Flow

interface TripDBRepository {
    suspend fun insertTrip(trip: TripEntity)

    suspend fun getAllTrips(): Flow<List<TripEntity>>
}