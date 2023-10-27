package com.example.turistaapp.create_trip.data.network.places_autocomplete.models // ktlint-disable package-name

import com.google.gson.annotations.SerializedName

data class PlacesAutocompleteResponseApiModel(
    @SerializedName("predictions") val placesAutocompletePredictionsApi: List<PlaceAutocompletePredictionApiModel>,
    // @SerializedName("status") val statusApi: String?
)
