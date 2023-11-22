package com.example.turistaapp.core.di

import com.example.turistaapp.create_trip.data.database.repository.TripDBRepository
import com.example.turistaapp.trip_details.domain.UpdateImagesFromDBUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class TripDetailsModule {

    @Singleton
    @Provides
    fun provideUpdateImagesFromDBUseCase(tripDBRepository: TripDBRepository) : UpdateImagesFromDBUseCase {
        return UpdateImagesFromDBUseCase(tripDBRepository)
    }

}