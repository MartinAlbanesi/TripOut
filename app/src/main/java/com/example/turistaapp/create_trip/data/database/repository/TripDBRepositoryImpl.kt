package com.example.turistaapp.create_trip.data.database.repository

import com.example.turistaapp.create_trip.data.database.dao.TripDao
import com.example.turistaapp.create_trip.data.database.entities.TripEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TripDBRepositoryImpl @Inject constructor(
    private val tripDao: TripDao
) : TripDBRepository {
    override suspend fun insertTrip(trip: TripEntity) {
        tripDao.insertTrip(trip)
    }

    override suspend fun getAllTrips(): Flow<List<TripEntity>> {
        return tripDao.getAllTrips()
    }
}