package com.example.turistaapp.core.di

import com.example.turistaapp.core.data.datastore.LocalDataStoreRepository
import com.example.turistaapp.map.data.DirectionsRepository
import com.example.turistaapp.map.data.IDirectionsRepository
import com.example.turistaapp.map.data.api.service.DirectionsApiService
import com.example.turistaapp.map.domain.GetIsMapTutorialComplete
import com.example.turistaapp.map.domain.GetRouteModel
import com.example.turistaapp.map.domain.SetIsMapTutorialComplete
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

    @Singleton
    @Provides
    fun provideGetIsMapTutorialComplete(localDataStoreRepository: LocalDataStoreRepository) : GetIsMapTutorialComplete {
        return GetIsMapTutorialComplete(localDataStoreRepository)
    }

    @Singleton
    @Provides
    fun provideSetIsMapTutorialComplete(localDataStoreRepository: LocalDataStoreRepository) : SetIsMapTutorialComplete {
        return SetIsMapTutorialComplete(localDataStoreRepository)
    }

}