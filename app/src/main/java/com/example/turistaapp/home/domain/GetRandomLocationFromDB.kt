package com.example.turistaapp.home.domain

import android.util.Log
import com.example.turistaapp.create_trip.domain.GetDestinationLocationsFromDataBase
import com.example.turistaapp.create_trip.domain.models.LocationModel
import javax.inject.Inject

class GetRandomLocationFromDB @Inject constructor(
    private val getDestinationLocationsFromDataBase: GetDestinationLocationsFromDataBase
) {
    suspend operator fun invoke(): LocationModel? {
        return getDestinationLocationsFromDataBase().randomOrNull()
    }
}