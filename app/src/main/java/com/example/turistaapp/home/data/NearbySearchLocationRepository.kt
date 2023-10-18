package com.example.turistaapp.home.data

import com.example.turistaapp.create_trip.data.mapper.toLocationModel
import com.example.turistaapp.create_trip.domain.models.LocationModel
import com.example.turistaapp.home.data.api.NearbySearchLocationApiService
import javax.inject.Inject

interface INearbySearchLocationRepository {

    suspend fun getNearbyLocation(location: String): List<LocationModel>?
}

class NearbySearchLocationRepository @Inject constructor(
    private val nearbySearchLocationApiService: NearbySearchLocationApiService,
) : INearbySearchLocationRepository {

    override suspend fun getNearbyLocation(
        location: String,
    ): List<LocationModel>? {
        val api = nearbySearchLocationApiService.searchNearbyPlaces(location)

        if (api.isSuccessful && api.code() == 200) {
            return api.body()!!.nearbyLocationsApi.map {
                it.toLocationModel()
            }
        }
        return null
    }
}
