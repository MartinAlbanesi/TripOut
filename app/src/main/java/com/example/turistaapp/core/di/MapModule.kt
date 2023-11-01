package com.example.turistaapp.core.di

import com.example.turistaapp.map.data.DirectionsRepository
import com.example.turistaapp.map.data.IDirectionsRepository
import com.example.turistaapp.map.data.api.service.DirectionsApiService
import com.example.turistaapp.map.domain.GetRouteModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MapModule {

    @Singleton
    @Provides
    fun provideDirectionsRepository(directionsService: DirectionsApiService): IDirectionsRepository {
        return DirectionsRepository(directionsService)
    }

    @Singleton
    @Provides
    fun provideGetRouteModel(directionsRepository: DirectionsRepository): GetRouteModel {
        return GetRouteModel(directionsRepository)
    }

}