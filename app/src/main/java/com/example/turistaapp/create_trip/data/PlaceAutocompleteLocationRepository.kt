package com.example.turistaapp.create_trip.data // ktlint-disable package-name

import android.util.Log
import com.example.turistaapp.create_trip.data.network.places_autocomplete.PlacesAutocompleteApiService
import com.example.turistaapp.create_trip.domain.models.PlaceAutocompletePredictionModel
import javax.inject.Inject

interface IPlaceAutocompleteLocationRepository {

    suspend fun getPlaceAutocompleteLocations(location: String): List<PlaceAutocompletePredictionModel>?
}

class PlaceAutocompleteLocationRepository @Inject constructor(
    private val placesAutocompleteApiService: PlacesAutocompleteApiService,
) : IPlaceAutocompleteLocationRepository {
    override suspend fun getPlaceAutocompleteLocations(location: String): List<PlaceAutocompletePredictionModel>? {
        val api = placesAutocompleteApiService.getPlaceAutocompletePredictions(location)

        // @Query("types") types: List<String> = listOf("establishment")

        if (api.isSuccessful) {
            val placeAutocompleteLocations = api.body()?.placesAutocompletePredictionsApi?.filter {
                it.typesApi.contains("street_address") || it.typesApi.contains("establishment") || it.typesApi.contains(
                    "route",
                ) || it.typesApi.contains("premise")
            }
                ?.map {
                    Log.d(
                        "PlaceAutocompleteLocationRepository",
                        "getPlaceAutocompleteLocations: ${it.typesApi}",
                    )
                    PlaceAutocompletePredictionModel(
                        placeId = it.placeIdApi,
                        description = it.descriptionApi,
                        distanceMeters = it.distanceMetersApi,
                        types = it.typesApi,
                    )
                }
            return placeAutocompleteLocations
        }
        return null
    }
}
