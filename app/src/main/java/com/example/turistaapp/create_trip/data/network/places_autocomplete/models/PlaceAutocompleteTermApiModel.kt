package com.example.turistaapp.create_trip.data.network.places_autocomplete.models

import com.google.gson.annotations.SerializedName

data class PlaceAutocompleteTermApiModel (
    @SerializedName("offset") val offsetApi: Int?,
    @SerializedName("value") val valueApi: String?
)
