package com.example.turistaapp.create_trip.data.network.places_autocomplete

import com.example.turistaapp.BuildConfig
import com.example.turistaapp.create_trip.data.network.places_autocomplete.models.PlacesAutocompleteResponseApiModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PlacesAutocompleteApiService {

    @GET("maps/api/place/autocomplete/json")
    suspend fun getPlaceAutocompletePredictions(
        @Query("input") input: String,
        @Query("radius") radius: Int = 5000,
        @Query("key") apiKey: String = BuildConfig.MAPS_API_KEY
    ): Response<PlacesAutocompleteResponseApiModel>
}