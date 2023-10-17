package com.example.turistaapp.create_trip.ui.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.turistaapp.create_trip.FakeDataBaseSource
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
import kotlinx.coroutines.test.setMain
import okhttp3.Dispatcher
import org.junit.After
import org.junit.Assert.*

import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CreateTripViewModelTest {

    @RelaxedMockK
    private lateinit var insertTripUseCase: InsertTripUseCase

    @RelaxedMockK
    private lateinit var getPlaceAutocompleteLocationsUseCase: GetPlaceAutocompleteLocationsUseCase

    @RelaxedMockK
    private lateinit var getPlaceDetailsUseCase: GetPlaceDetailsUseCase

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var createTripViewModel: CreateTripViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

//        dispatcher = Dispatchers.Unconfined
        createTripViewModel = CreateTripViewModel(
            insertTripUseCase,
            getPlaceAutocompleteLocationsUseCase,
            getPlaceDetailsUseCase
        )
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun tearDown() {
        Dispatchers.shutdown()
    }

    @Test
    fun cuandoElCasoDeUsoGetPlaceDetailsRetorneLocationModelEntoncesSeteaEnUnaVariableLiveData() = runTest {
        //Given
        val expected = FakeDataBaseSource.locationModel
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