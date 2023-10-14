package com.example.turistaapp.create_trip.data.database.repository

import android.util.Log
import com.example.turistaapp.create_trip.data.database.dao.TripDao
import com.example.turistaapp.create_trip.data.database.entities.TripEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TripDBRepositoryImpl @Inject constructor(
    private val tripDao: TripDao
) : TripDBRepository {
    override suspend fun insertTrip(trip: TripEntity) {
        Log.d("Prueba", "insertTrip: $trip")
        tripDao.insertTrip(trip)
    }

    override suspend fun getAllTrips(): Flow<List<TripEntity>> {
        return tripDao.getAllTrips()
    }

    override suspend fun getLocationsFromDestination(): List<String> {
        return tripDao.getLocationsFromDestination()
    }

    override suspend fun getFlowLocationsFromDestination(): Flow<List<String>> {
        return tripDao.getFlowLocationsFromDestination()
    }
}