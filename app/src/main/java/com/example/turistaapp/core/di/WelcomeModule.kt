package com.example.turistaapp.core.di

import com.example.turistaapp.core.data.datastore.LocalDataStoreRepository
import com.example.turistaapp.welcome.domain.GetNameFromDataStore
import com.example.turistaapp.welcome.domain.SetNameFromDataStore
import dagger.Module
import dagger.Provides

import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class WelcomeModule {

    @Singleton
    @Provides
    fun provideGetNameFromDataStore(localDataStoreRepository : LocalDataStoreRepository) : GetNameFromDataStore {
        return GetNameFromDataStore(localDataStoreRepository)
    }

    @Singleton
    @Provides
    fun provideSetNameFromDataStore(localDataStoreRepository : LocalDataStoreRepository) : SetNameFromDataStore {
        return SetNameFromDataStore(localDataStoreRepository)
    }
}