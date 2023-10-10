package com.example.turistaapp.create_trip.data.network.places_autocomplete.models

import com.google.gson.annotations.SerializedName

data class PlaceAutocompleteStructuredFormatApiModel (
    @SerializedName("main_text") val mainTextApi: String?,
    @SerializedName("main_text_matched_substrings") val mainTextMatchedSubstringsApi: List<PlaceAutocompleteMatchedSubstringApiModel>,
    @SerializedName("secondary_text") val secondaryTextApi: String?
)