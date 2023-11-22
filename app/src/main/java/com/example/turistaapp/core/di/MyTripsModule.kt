package com.example.turistaapp.core.di

import com.example.turistaapp.core.data.database.TripDBRepository
import com.example.turistaapp.create_trip.domain.DeleteTripUseCase
import com.example.turistaapp.my_trips.domain.GetTripsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MyTripsModule {

    @Provides
    @Singleton
    fun provideGetTripsUseCase(tripDBRepository: TripDBRepository): GetTripsUseCase {
        return GetTripsUseCase(tripDBRepository)
    }

    fun provideDeleteTripUseCase(tripDBRepository: TripDBRepository): DeleteTripUseCase {
        return DeleteTripUseCase(tripDBRepository)
    }
}
