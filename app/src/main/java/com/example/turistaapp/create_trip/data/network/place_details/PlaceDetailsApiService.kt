package com.example.turistaapp.create_trip.data.network.place_details

import com.example.turistaapp.BuildConfig
import com.example.turistaapp.create_trip.data.network.place_details.models.PlacesDetailsResponseApiModel
import com.example.turistaapp.create_trip.data.network.places_autocomplete.models.PlacesAutocompleteResponseApiModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PlaceDetailsApiService {

    @GET("maps/api/place/details/json")
    suspend fun getPlaceDetails(
        @Query("place_id)") placeId: String,
        //@Query("radius") radius: Int = 5000,
        @Query("key") apiKey: String = BuildConfig.MAPS_API_KEY
    ): Response<PlacesDetailsResponseApiModel>

}