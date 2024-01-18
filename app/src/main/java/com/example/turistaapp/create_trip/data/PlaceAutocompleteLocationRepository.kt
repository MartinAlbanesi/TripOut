package com.example.turistaapp.create_trip.data // ktlint-disable package-name

import com.example.turistaapp.create_trip.data.network.places_autocomplete.PlacesAutocompleteApiService
import com.example.turistaapp.create_trip.domain.models.PlaceAutocompletePredictionModel
import com.example.turistaapp.create_trip.domain.models.PlaceAutocompleteStructuredFormatModel
import javax.inject.Inject

interface IPlaceAutocompleteLocationRepository {

    suspend fun getPlaceAutocompleteLocations(location: String, type: String): List<PlaceAutocompletePredictionModel>?
}

class PlaceAutocompleteLocationRepository @Inject constructor(
    private val placesAutocompleteApiService: PlacesAutocompleteApiService,
) : IPlaceAutocompleteLocationRepository {
    override suspend fun getPlaceAutocompleteLocations(location: String, type: String): List<PlaceAutocompletePredictionModel>? {
        val api = placesAutocompleteApiService.getPlaceAutocompletePredictions(location, type)

        if (api.isSuccessful) {
            val placeAutocompleteLocations = api.body()?.placesAutocompletePredictionsApi
                ?.filter {
                !it.typesApi.contains("country") && !it.typesApi.contains("continent") && !it.typesApi.contains("political") && !it.typesApi.contains("locality")
//                !it.typesApi.contains("geocode")
                }
                ?.map {
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
