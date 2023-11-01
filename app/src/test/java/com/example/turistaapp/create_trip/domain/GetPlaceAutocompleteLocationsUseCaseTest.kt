package com.example.turistaapp.create_trip.domain

import com.example.turistaapp.create_trip.data.IPlaceAutocompleteLocationRepository
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import org.junit.Before
import org.junit.Test

class GetPlaceAutocompleteLocationsUseCaseTest {
    private lateinit var getPlaceAutocompleteLocationsUseCase: GetPlaceAutocompleteLocationsUseCase

    @RelaxedMockK
    private lateinit var placeAutocompleteRepository: IPlaceAutocompleteLocationRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        getPlaceAutocompleteLocationsUseCase =
            GetPlaceAutocompleteLocationsUseCase(placeAutocompleteRepository)
    }

    @Test
    fun `invoke - placeAutocompleteRepository - return PlaceAutocompletePredictionModel list`() {
        // Given

        // When

        // Then
    }
}
