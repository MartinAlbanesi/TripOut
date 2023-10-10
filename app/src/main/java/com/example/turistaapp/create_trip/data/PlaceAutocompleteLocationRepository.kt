package com.example.turistaapp.create_trip.data

import com.example.turistaapp.create_trip.data.network.places_autocomplete.PlacesAutocompleteApiService
import com.example.turistaapp.create_trip.domain.models.PlaceAutocompletePredictionModel
import javax.inject.Inject

interface IPlaceAutocompleteLocationRepository {

    suspend fun getPlaceAutocompleteLocations(location: String): List<PlaceAutocompletePredictionModel>?

}
class PlaceAutocompleteLocationRepository @Inject constructor(
    private val placesAutocompleteApiService: PlacesAutocompleteApiService
): IPlaceAutocompleteLocationRepository{
    override suspend fun getPlaceAutocompleteLocations(location: String): List<PlaceAutocompletePredictionModel>? {
        val api = placesAutocompleteApiService.getPlaceAutocompletePredictions(location)

        if (api.isSuccessful && api.code() == 200) {
            val placeAutocompleteLocations = api.body()?.placesAutocompletePredictionsApi?.map {
                PlaceAutocompletePredictionModel(
                    description = it.descriptionApi,
                    distance_meters = it.distanceMetersApi,
                    types = it.typesApi
                )
            }
            return placeAutocompleteLocations
        }
        return null
    }

}