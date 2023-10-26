package com.example.turistaapp.home.domain

import com.example.turistaapp.home.data.IDirectionsRepository
import com.example.turistaapp.home.domain.models.RouteModel
import javax.inject.Inject

class GetRouteModel @Inject constructor(
    private val directionsRepository: IDirectionsRepository
){
    suspend operator fun invoke(
        origin: String,
        destination: String,
        mode: String
    ): RouteModel? {
        return directionsRepository.getDirections(origin, destination, mode)
    }
}