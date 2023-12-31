package com.example.turistaapp.core.di

import android.content.Context
import androidx.room.Room
import com.example.turistaapp.core.data.database.TripDBRepository
import com.example.turistaapp.core.data.database.TripDBRepositoryImpl
import com.example.turistaapp.core.data.database.TripDataBase
import com.example.turistaapp.core.data.database.dao.TripDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun roomProvide(@ApplicationContext applicationContext: Context) =
        Room.databaseBuilder(
            applicationContext,
            TripDataBase::class.java,
            "app_database",
        ).build()

    @Singleton
    @Provides
    fun tripDaoProvide(appDataBase: TripDataBase): TripDao {
        return appDataBase.getTripDao()
    }

    @Singleton
    @Provides
    fun provideTripDBRepository(tripDao: TripDao): TripDBRepository {
        return TripDBRepositoryImpl(tripDao)
    }
}
