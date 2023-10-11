package com.example.turistaapp.core.di

import com.example.turistaapp.home.data.INearbySearchLocationRepository
import com.example.turistaapp.home.data.NearbySearchLocationRepository
import com.example.turistaapp.home.data.api.NearbySearchLocationApiService
import com.example.turistaapp.home.domain.GetNearbyLocationsUseCase
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
    fun provideNearbySearchLocationRepository(nearbySearchLocationApiService: NearbySearchLocationApiService) : INearbySearchLocationRepository{
        return NearbySearchLocationRepository(nearbySearchLocationApiService)
    }

    @Provides
    @Singleton
    fun provideGetResultList(nearbySearchLocationRepository: INearbySearchLocationRepository) : GetNearbyLocationsUseCase{
        return GetNearbyLocationsUseCase(nearbySearchLocationRepository)
    }

    @Provides
    @Singleton
    fun provideDispatcher() : CoroutineDispatcher{
        return Dispatchers.IO
    }
}

