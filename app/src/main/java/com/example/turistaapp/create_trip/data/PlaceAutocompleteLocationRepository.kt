package com.example.turistaapp.create_trip.data // ktlint-disable package-name

import android.util.Log
import com.example.turistaapp.create_trip.data.network.places_autocomplete.PlacesAutocompleteApiService
import com.example.turistaapp.create_trip.domain.models.PlaceAutocompletePredictionModel
import com.example.turistaapp.create_trip.domain.models.PlaceAutocompleteStructuredFormatModel
import javax.inject.Inject

interface IPlaceAutocompleteLocationRepository {

    suspend fun getPlaceAutocompleteLocations(location: String): List<PlaceAutocompletePredictionModel>?
}

class PlaceAutocompleteLocationRepository @Inject constructor(
    private val placesAutocompleteApiService: PlacesAutocompleteApiService,
) : IPlaceAutocompleteLocationRepository {
    override suspend fun getPlaceAutocompleteLocations(location: String): List<PlaceAutocompletePredictionModel>? {
        val api = placesAutocompleteApiService.getPlaceAutocompletePredictions(location)

        if (api.isSuccessful) {
            val placeAutocompleteLocations = api.body()?.placesAutocompletePredictionsApi?.filter {
                !it.typesApi.contains("country")
                !it.typesApi.contains("continent")
                !it.typesApi.contains("geocode")
            }?.map {
                PlaceAutocompletePredictionModel(
                    placeId = it.placeIdApi,
                    description = it.descriptionApi,
                    distanceMeters = it.distanceMetersApi,
                    types = it.typesApi,
                    structured_formatting = it.structuredFormattingApi.let { structuredFormattingApi ->
                        PlaceAutocompleteStructuredFormatModel(
                            main_text = structuredFormattingApi.mainTextApi,
                            secondary_text = structuredFormattingApi.secondaryTextApi,
                        )
                    },
                )
            }
            return placeAutocompleteLocations
        }
        return null
    }
}
