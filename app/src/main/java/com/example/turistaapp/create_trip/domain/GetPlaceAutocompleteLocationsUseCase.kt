package com.example.turistaapp.create_trip.domain

import android.util.Log
import com.example.turistaapp.create_trip.data.IPlaceAutocompleteLocationRepository
import com.example.turistaapp.create_trip.domain.models.PlaceAutocompletePredictionModel
import javax.inject.Inject

class GetPlaceAutocompleteLocationsUseCase @Inject constructor(
    private val placeAutocompleteRepository: IPlaceAutocompleteLocationRepository
) {

    suspend operator fun invoke(location: String): List<PlaceAutocompletePredictionModel>? {
        return placeAutocompleteRepository.getPlaceAutocompleteLocations(location)
    }
}