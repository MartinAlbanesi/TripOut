package com.example.turistaapp.create_trip.domain

import com.example.turistaapp.create_trip.data.IPlaceDetailsRepository
import com.example.turistaapp.create_trip.domain.models.LocationModel
import javax.inject.Inject

class GetPlaceDetailsUseCase @Inject constructor(
    private val placeDetailsRepository: IPlaceDetailsRepository
) {

    suspend operator fun invoke(placeID: String): LocationModel? {
        return  placeDetailsRepository.getPlaceDetails(placeID)
    }
}