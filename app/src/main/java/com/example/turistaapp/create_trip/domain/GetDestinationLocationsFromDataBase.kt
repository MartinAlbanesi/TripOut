package com.example.turistaapp.create_trip.domain

import android.util.Log
import com.example.turistaapp.core.utils.GsonConverter
import com.example.turistaapp.create_trip.data.database.repository.TripDBRepository
import com.example.turistaapp.create_trip.domain.models.LocationModel
import javax.inject.Inject

class GetDestinationLocationsFromDataBase @Inject constructor(
    private val tripDBRepository: TripDBRepository
) {
    suspend operator fun invoke() : List<LocationModel>{
        val tripsLocations = tripDBRepository.getLocationsFromDestination()
        if(tripsLocations.isEmpty()){
           return emptyList()
        }
//        Log.i("titi", tripsLocations.toString())
        return tripsLocations.map {
            Log.i("titi", GsonConverter.fromJson(it, LocationModel::class.java).toString())
            GsonConverter.fromJson(it, LocationModel::class.java)
        }
    }
}