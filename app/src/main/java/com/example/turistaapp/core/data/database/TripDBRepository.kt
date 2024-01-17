package com.example.turistaapp.core.data.database // ktlint-disable package-name

import android.net.Uri
import com.example.turistaapp.core.data.database.entities.TripEntity
import kotlinx.coroutines.flow.Flow

interface TripDBRepository {
    suspend fun insertTrip(trip: TripEntity)

    suspend fun getAllTrips(): Flow<List<TripEntity>>

    suspend fun getLocationsFromDestination(): List<String>

    suspend fun getFlowLocationsFromDestination(): Flow<List<String>>

    suspend fun deleteTrip(trip: TripEntity)

    suspend fun updateImages(id: Int, images: List<Uri>)
}
