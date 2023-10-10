package com.example.turistaapp.create_trip.data.network.places_autocomplete.models

import com.google.gson.annotations.SerializedName

data class PlaceAutocompletePredictionApiModel(
    @SerializedName("description") val descriptionApi: String?,
    @SerializedName("matched_substrings") val matchedSubstringsApi: List<PlaceAutocompleteMatchedSubstringApiModel>,
    @SerializedName("structured_formatting") val structuredFormattingApi: PlaceAutocompleteStructuredFormatApiModel,
    @SerializedName("terms") val termsApi: List<PlaceAutocompleteTermApiModel>,
    @SerializedName("distance_meters") val distanceMetersApi: Int?,
    @SerializedName("place_id") val placeIdApi: String?,
    @SerializedName("types") val typesApi: List<String>
)