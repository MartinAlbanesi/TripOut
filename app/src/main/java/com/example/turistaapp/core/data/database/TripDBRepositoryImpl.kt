package com.example.turistaapp.core.data.database // ktlint-disable package-name

import android.net.Uri
import com.example.turistaapp.core.data.database.dao.TripDao
import com.example.turistaapp.core.data.database.entities.TripEntity
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

    override suspend fun updateImages(id: Int, images: Uri) {
        val tripImages = tripDao.getTripById(id).images?.toMutableList() ?: mutableListOf()
        tripImages.add(images.toString())
        tripDao.updateImages(id, tripImages)
    }
}
