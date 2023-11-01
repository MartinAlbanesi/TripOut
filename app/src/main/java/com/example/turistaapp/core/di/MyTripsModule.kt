package com.example.turistaapp.core.di

import com.example.turistaapp.create_trip.data.database.repository.TripDBRepository
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
}
