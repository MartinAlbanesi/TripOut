package com.example.turistaapp.home.data.api.service

import com.example.turistaapp.BuildConfig
import com.example.turistaapp.home.data.api.model.DirectionsApi
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface DirectionsApiService {

    @GET("/maps/api/directions/json")
    suspend fun getDirection(
        @Query("origin") origin: String,
        @Query("destination") destination: String,
        @Query("key") apiKey: String = BuildConfig.MAPS_API_KEY,
        @Query("mode") mode: String = "driving",
    ): Response<DirectionsApi>
}