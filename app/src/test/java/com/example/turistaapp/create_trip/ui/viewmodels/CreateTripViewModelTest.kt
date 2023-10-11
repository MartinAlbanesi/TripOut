package com.example.turistaapp.create_trip.ui.viewmodels

import com.example.turistaapp.create_trip.domain.GetPlaceAutocompleteLocationsUseCase
import com.example.turistaapp.create_trip.domain.GetPlaceDetailsUseCase
import com.example.turistaapp.create_trip.domain.InsertTripUseCase
import com.example.turistaapp.create_trip.domain.models.LocationModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.runTest
import okhttp3.Dispatcher
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class CreateTripViewModelTest {

    @RelaxedMockK
    private lateinit var insertTripUseCase: InsertTripUseCase

    @RelaxedMockK
    private lateinit var getPlaceAutocompleteLocationsUseCase: GetPlaceAutocompleteLocationsUseCase

    @RelaxedMockK
    private lateinit var getPlaceDetailsUseCase: GetPlaceDetailsUseCase

    private lateinit var dispatcher: CoroutineDispatcher

    private lateinit var createTripViewModel: CreateTripViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        dispatcher = Dispatchers.Unconfined
        createTripViewModel = CreateTripViewModel(
            insertTripUseCase,
            getPlaceAutocompleteLocationsUseCase,
            getPlaceDetailsUseCase,
            dispatcher
        )
    }

    @Test
    fun cuandoElCasoDeUsoGetPlaceDetailsRetorneLocationModelEntoncesSeteaEnUnaVariableLiveData() = runTest {
        //Given
        val expected = LocationModel("placeId", "name", "", 0.0, 0, "phone", 0.0, 0.0, listOf(""))

        coEvery {
            getPlaceDetailsUseCase(any())
        }.returns(expected)

        //When
        createTripViewModel.onCreateTripClick()

        //Then
        val result = createTripViewModel.originLocation.value
        assertEquals(expected, result)
    }
}