package com.example.turistaapp.home.data.api

import com.example.turistaapp.home.data.api.model.NearbySearchLocationApi
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NearbySearchLocationApiService {

    @GET("/maps/api/place/nearbysearch/json")
    suspend fun searchNearbyPlaces(
        @Query("location") location: String,
        @Query("radius") radius: Int = 5000,
        @Query("type") type: String = "point_of_interest",
        @Query("key") apiKey: String = "AIzaSyCPj0hmVS-0SKTDWGFNCgPdkQSFuQnD4Ps"
    ): Response<NearbySearchLocationApi>

}