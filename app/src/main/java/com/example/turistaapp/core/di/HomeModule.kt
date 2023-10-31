package com.example.turistaapp.core.di

import android.content.Context
import com.example.turistaapp.create_trip.domain.GetDestinationLocationsFromDataBase
import com.example.turistaapp.home.data.INearbySearchLocationRepository
import com.example.turistaapp.home.data.NearbySearchLocationRepository
import com.example.turistaapp.home.data.api.service.NearbySearchLocationApiService
import com.example.turistaapp.home.domain.GetNearbyLocationsUseCase
import com.example.turistaapp.home.domain.GetRandomLocationFromDB
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
    fun provideFusedLocationProviderClient(@ApplicationContext context: Context) : FusedLocationProviderClient {
        return LocationServices.getFusedLocationProviderClient(context)
    }
}
