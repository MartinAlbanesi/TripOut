package com.example.turistaapp.home.data

import com.example.turistaapp.home.data.api.NearbySearchLocationApiService
import com.example.turistaapp.home.domain.models.NearbyLocation
import javax.inject.Inject

interface INearbySearchLocationRepository {

    suspend fun getNearbyLocation(location: String): List<NearbyLocation>?

}

class NearbySearchLocationRepository @Inject constructor(
    private val nearbySearchLocationApiService: NearbySearchLocationApiService
) : INearbySearchLocationRepository {

    override suspend fun getNearbyLocation(
        location: String
    ): List<NearbyLocation>? {

        val api = nearbySearchLocationApiService.searchNearbyPlaces(location)

        if (api.isSuccessful && api.code() == 200) {
            val nearbyLocations = api.body()?.nearbyLocationApis?.map {
                NearbyLocation(
                    name = it.name,
                    photoUrl = it.getPhoto(),
                    rating = it.rating,
                    userRating = it.userRatings,
                    direction = it.direction,
                    lat = it.getLat(),
                    lng = it.getLng()
                )
            }
            return nearbyLocations
        }
        return null
    }

}