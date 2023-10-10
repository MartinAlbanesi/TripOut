package com.example.turistaapp.core.di

import com.example.turistaapp.create_trip.data.IPlaceAutocompleteLocationRepository
import com.example.turistaapp.create_trip.data.PlaceAutocompleteLocationRepository
import com.example.turistaapp.create_trip.data.network.places_autocomplete.PlacesAutocompleteApiService
import com.example.turistaapp.create_trip.domain.GetPlaceAutocompleteLocationsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CreateTripModule {

    @Provides
    @Singleton
    fun provideNearbySearchLocationRepository(placesAutocompleteApiService: PlacesAutocompleteApiService) : IPlaceAutocompleteLocationRepository {
        return PlaceAutocompleteLocationRepository(placesAutocompleteApiService)
    }

    @Provides
    @Singleton
    fun provideGetResultList(placeAutocompleteLocationRepository: IPlaceAutocompleteLocationRepository) : GetPlaceAutocompleteLocationsUseCase{
        return GetPlaceAutocompleteLocationsUseCase(placeAutocompleteLocationRepository)
    }
}