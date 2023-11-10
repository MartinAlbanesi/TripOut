package com.example.turistaapp.create_trip.data.database.repository // ktlint-disable package-name

import com.example.turistaapp.create_trip.data.database.dao.TripDao
import com.example.turistaapp.create_trip.data.database.entities.TripEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TripDBRepositoryImpl @Inject constructor(
    private val tripDao: TripDao,
) : TripDBRepository {
    override suspend fun insertTrip(trip: TripEntity) {
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

    override suspend fun deleteTrip(trip: TripEntity) {
        tripDao.deleteTrip(trip)
    }
}
