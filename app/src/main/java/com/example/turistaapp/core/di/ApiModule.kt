package com.example.turistaapp.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @Provides
    @Singleton
    fun provideRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://maps.googleapis.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideGooglePlacesApiService(retrofit: Retrofit) : NearbySearchLocationApiService {
        return retrofit.create(NearbySearchLocationApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideGooglePlaceAutocompleteApiService(retrofit: Retrofit) : PlacesAutocompleteApiService {
        return retrofit.create(PlacesAutocompleteApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideGooglePlaceDetailsApiService(retrofit: Retrofit) : PlaceDetailsApiService {
        return retrofit.create(PlaceDetailsApiService::class.java)
    }

}