package com.example.turistaapp.core.di

import com.example.turistaapp.create_trip.data.IPlaceAutocompleteLocationRepository
import com.example.turistaapp.create_trip.data.IPlaceDetailsRepository
import com.example.turistaapp.create_trip.data.PlaceAutocompleteLocationRepository
import com.example.turistaapp.create_trip.data.PlaceDetailsRepository
import com.example.turistaapp.create_trip.data.network.place_details.PlaceDetailsApiService
import com.example.turistaapp.create_trip.data.network.places_autocomplete.PlacesAutocompleteApiService
import com.example.turistaapp.create_trip.domain.GetPlaceAutocompleteLocationsUseCase
import com.example.turistaapp.create_trip.domain.GetPlaceDetailsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CreateTripModule {

    @Provides
    @Singleton
    fun providePlaceAutocompleteLocationRepository(placesAutocompleteApiService: PlacesAutocompleteApiService) : IPlaceAutocompleteLocationRepository {
        return PlaceAutocompleteLocationRepository(placesAutocompleteApiService)
    }

    @Provides
    @Singleton
    fun provideGetResultList(placeAutocompleteLocationRepository: IPlaceAutocompleteLocationRepository) : GetPlaceAutocompleteLocationsUseCase{
        return GetPlaceAutocompleteLocationsUseCase(placeAutocompleteLocationRepository)
    }

    @Provides
    @Singleton
    fun providePlaceDetailsRepository(placesDetailsApiService: PlaceDetailsApiService) : IPlaceDetailsRepository {
        return PlaceDetailsRepository(placesDetailsApiService)
    }

    @Provides
    @Singleton
    fun provideGetPlaceDetailsResult(placeDetailsRepository: IPlaceDetailsRepository) : GetPlaceDetailsUseCase {
        return GetPlaceDetailsUseCase(placeDetailsRepository)
    }
}