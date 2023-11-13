package com.example.turistaapp.create_trip.data.database.repository // ktlint-disable package-name

import com.example.turistaapp.create_trip.data.database.entities.TripEntity
import kotlinx.coroutines.flow.Flow

interface TripDBRepository {
    suspend fun insertTrip(trip: TripEntity)

    suspend fun getAllTrips(): Flow<List<TripEntity>>

    suspend fun getLocationsFromDestination(): List<String>

    suspend fun getFlowLocationsFromDestination(): Flow<List<String>>

    suspend fun deleteTrip(trip: TripEntity)
}
