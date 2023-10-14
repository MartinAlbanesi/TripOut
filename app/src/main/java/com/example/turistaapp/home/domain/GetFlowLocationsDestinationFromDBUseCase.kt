package com.example.turistaapp.home.domain

import android.util.Log
import com.example.turistaapp.core.utils.GsonConverter
import com.example.turistaapp.create_trip.data.database.repository.TripDBRepository
import com.example.turistaapp.create_trip.domain.models.LocationModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetFlowLocationsDestinationFromDBUseCase @Inject constructor(
    private val tripDBRepository: TripDBRepository
) {

    suspend operator fun invoke(): Flow<List<LocationModel>> {
        val destinationLocationsFlow = tripDBRepository.getFlowLocationsFromDestination().first()

        val locationModelFlow = flow {
            val destinationLocationToModel = destinationLocationsFlow.map {
                GsonConverter.fromJson(it, LocationModel::class.java)
            }
            emit(destinationLocationToModel)
        }

        val combine = tripDBRepository.getFlowLocationsFromDestination().combine(locationModelFlow){
            _, locationModels -> locationModels
        }

        return combine
    }

}