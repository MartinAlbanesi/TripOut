package com.example.turistaapp.home.data

import com.example.turistaapp.home.data.api.service.DirectionsApiService
import com.example.turistaapp.home.data.mappers.toDirectionsModel
import com.example.turistaapp.home.domain.models.DirectionsModel
import javax.inject.Inject

interface IDirectionsRepository {
    suspend fun getDirections(
        origin: String,
        destination: String,
        mode: String,
    ): DirectionsModel?
}

class DirectionsRepository @Inject constructor(
    private val directionsApiService: DirectionsApiService
) : IDirectionsRepository {
    override suspend fun getDirections(
        origin: String,
        destination: String,
        mode: String
    ): DirectionsModel? {
        val api = directionsApiService.getDirection(origin, destination, mode)
        if (!api.isSuccessful) {
            return null
        }
        return api.body()?.toDirectionsModel()
    }

}