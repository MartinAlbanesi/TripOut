package com.example.turistaapp.core.di

import com.example.turistaapp.create_trip.data.database.repository.TripDBRepository
import com.example.turistaapp.setting.domain.UpdateImagesFromDBUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class TripDetails {

    @Singleton
    @Provides
    fun provideUpdateImagesFromDBUseCase(tripDBRepository: TripDBRepository) : UpdateImagesFromDBUseCase {
        return UpdateImagesFromDBUseCase(tripDBRepository)
    }

}