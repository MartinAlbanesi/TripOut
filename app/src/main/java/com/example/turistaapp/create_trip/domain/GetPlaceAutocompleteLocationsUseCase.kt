package com.example.turistaapp.create_trip.domain

import com.example.turistaapp.create_trip.data.PlacesAutocompleteLocationRepository
import com.example.turistaapp.create_trip.domain.models.PlaceAutocompleteModel
import javax.inject.Inject

class GetPlaceAutocompleteLocationsUseCase @Inject constructor(
    private val placeAutocompleteRepository: PlacesAutocompleteLocationRepository
) {

    suspend operator fun invoke(location: String): List<PlaceAutocompleteModel>? {
        return placeAutocompleteRepository.getPlaceAutocompleteLocations(location)
    }
}