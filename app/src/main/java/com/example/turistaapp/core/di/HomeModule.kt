package com.example.turistaapp.core.di

import com.example.turistaapp.create_trip.domain.GetDestinationLocationsFromDataBase
import com.example.turistaapp.map.data.DirectionsRepository
import com.example.turistaapp.map.data.IDirectionsRepository
import com.example.turistaapp.map.data.INearbySearchLocationRepository
import com.example.turistaapp.map.data.NearbySearchLocationRepository
import com.example.turistaapp.map.data.api.service.DirectionsApiService
import com.example.turistaapp.map.data.api.service.NearbySearchLocationApiService
import com.example.turistaapp.map.domain.GetNearbyLocationsUseCase
import com.example.turistaapp.map.domain.GetRandomLocationFromDB
import com.example.turistaapp.map.domain.GetRouteModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class HomeModule {

    @Provides
    @Singleton
    fun provideNearbySearchLocationRepository(nearbySearchLocationApiService: NearbySearchLocationApiService): INearbySearchLocationRepository {
        return NearbySearchLocationRepository(nearbySearchLocationApiService)
    }

    @Provides
    @Singleton
    fun provideGetResultList(nearbySearchLocationRepository: INearbySearchLocationRepository): GetNearbyLocationsUseCase {
        return GetNearbyLocationsUseCase(nearbySearchLocationRepository)
    }

    @Provides
    @Singleton
    fun provideDispatcher(): CoroutineDispatcher {
        return Dispatchers.IO
    }

    @Singleton
    @Provides
    fun provideGetRandomLocationFromDB(getDestinationLocationsFromDataBase: GetDestinationLocationsFromDataBase): GetRandomLocationFromDB {
        return GetRandomLocationFromDB(getDestinationLocationsFromDataBase)
    }

    @Singleton
    @Provides
    fun provideDirectionsRepository(directionsService : DirectionsApiService) : IDirectionsRepository {
        return DirectionsRepository(directionsService)
    }

    @Singleton
    @Provides
    fun provideGetRouteModel(directionsRepository: DirectionsRepository) : GetRouteModel {
        return GetRouteModel(directionsRepository)
    }


}
