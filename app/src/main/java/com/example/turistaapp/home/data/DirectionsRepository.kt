package com.example.turistaapp.home.data

import com.example.turistaapp.home.data.api.service.DirectionsApiService
import com.example.turistaapp.home.data.mappers.toRouteModel
import com.example.turistaapp.home.domain.models.RouteModel
import javax.inject.Inject

interface IDirectionsRepository {
    suspend fun getDirections(
        origin: String,
        destination: String,
        mode: String,
    ): RouteModel?
}

class DirectionsRepository @Inject constructor(
    private val directionsApiService: DirectionsApiService
) : IDirectionsRepository {
    override suspend fun getDirections(
        origin: String,
        destination: String,
        mode: String
    ): RouteModel? {
        val api = directionsApiService.getDirection(origin, destination, mode)
        return api.body()?.routes?.get(0)?.toRouteModel()
    }

}