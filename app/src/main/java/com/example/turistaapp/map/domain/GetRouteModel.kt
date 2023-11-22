package com.example.turistaapp.map.domain

import com.example.turistaapp.create_trip.domain.models.TripModel
import com.example.turistaapp.map.data.IDirectionsRepository
import com.example.turistaapp.map.domain.models.RouteModel
import javax.inject.Inject

class GetRouteModel @Inject constructor(
    private val directionsRepository: IDirectionsRepository,
) {
    suspend operator fun invoke(
        origin: String,
        destination: String,
        transport: String,
        trip: TripModel? = null,
    ): RouteModel? {
        return directionsRepository.getDirections(origin, destination, transport)?.copy(trip = trip)
    }
}
