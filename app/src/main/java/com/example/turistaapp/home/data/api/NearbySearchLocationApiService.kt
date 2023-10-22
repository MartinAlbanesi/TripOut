package com.example.turistaapp.home.data.api

import com.example.turistaapp.BuildConfig
import com.example.turistaapp.home.data.api.model.NearbySearchLocationApi
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NearbySearchLocationApiService {

    @GET("/maps/api/place/nearbysearch/json")
    suspend fun searchNearbyPlaces(
        @Query("location") location: String,
        @Query("radius") radius: Int = 200,
        @Query("type") type: String = "point_of_interest,natural_feature,food,parkairport",
        @Query("key") apiKey: String = BuildConfig.MAPS_API_KEY,
    ): Response<NearbySearchLocationApi>
}
