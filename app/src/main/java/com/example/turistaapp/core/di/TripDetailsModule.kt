package com.example.turistaapp.core.di

import com.example.turistaapp.core.data.database.TripDBRepository
import com.example.turistaapp.setting.domain.UpdateImagesFromDBUseCase
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
    fun provideUpdateImagesFromDBUseCase(tripDBRepository: TripDBRepository): UpdateImagesFromDBUseCase {
        return UpdateImagesFromDBUseCase(tripDBRepository)
    }
}
