package com.example.turistaapp.map.data

import com.example.turistaapp.map.data.api.service.DirectionsApiService
import com.example.turistaapp.map.data.mappers.toRouteModel
import com.example.turistaapp.map.domain.models.RouteModel
import javax.inject.Inject

interface IDirectionsRepository {
    suspend fun getDirections(
        origin: String,
        destination: String,
        mode: String,
    ): RouteModel?
}

class DirectionsRepository @Inject constructor(
    private val directionsApiService: DirectionsApiService,
) : IDirectionsRepository {
    override suspend fun getDirections(
        origin: String,
        destination: String,
        mode: String,
    ): RouteModel? {
        val api = directionsApiService.getDirection(origin, destination, mode)
        return api.body()?.routes?.get(0)?.toRouteModel()
    }
}
