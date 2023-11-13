package com.example.turistaapp.map.data.api.service

import com.example.turistaapp.BuildConfig
import com.example.turistaapp.map.data.api.model.DirectionsApi
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface DirectionsApiService {

    @GET("/maps/api/directions/json")
    suspend fun getDirection(
        @Query("origin") origin: String,
        @Query("destination") destination: String,
        @Query("mode") mode: String = "driving",
        @Query("key") apiKey: String = BuildConfig.MAPS_API_KEY,
    ): Response<DirectionsApi>
}