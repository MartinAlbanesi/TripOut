package com.example.turistaapp.map.ui

import com.example.turistaapp.core.utils.ResponseUiState
import com.example.turistaapp.my_trips.domain.GetTripsUseCase
import com.example.turistaapp.home.domain.GetNearbyLocationsUseCase
import com.example.turistaapp.home.domain.GetRandomLocationFromDB
import com.example.turistaapp.map.domain.GetRouteModel
import com.example.turistaapp.map.fake.FakeDataSource
import com.example.turistaapp.map.ui.viewmodel.MapViewModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class MapViewModelTest {

    private lateinit var mapViewModel: MapViewModel

    @OptIn(ExperimentalCoroutinesApi::class)
    private val dispatcher = UnconfinedTestDispatcher()

    @RelaxedMockK
    private lateinit var getNearbyLocationsUseCase: GetNearbyLocationsUseCase

    @RelaxedMockK
    private lateinit var getRandomLocationFromDB: GetRandomLocationFromDB

    @RelaxedMockK
    private lateinit var getTripsUseCase: GetTripsUseCase

    @RelaxedMockK
    private lateinit var getRouteModel: GetRouteModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        mapViewModel = MapViewModel(
            dispatcher,
            getNearbyLocationsUseCase,
            getRandomLocationFromDB,
            getTripsUseCase,
            getRouteModel
        )
    }

    @Test
    fun setNearbyLocations_whenGetNearbyLocationUseCaseReturnNull_thenNearbyLocationsIsError() = runTest {
        coEvery { getNearbyLocationsUseCase(any()) } returns null

        mapViewModel.setNearbyLocations(0.0, 0.0)

        val actual = mapViewModel.nearbyLocations.value

        val expected = ResponseUiState.Error("No se encontraron lugares cercanos")

        assertEquals(expected.message, (actual as ResponseUiState.Error).message)
        assertEquals(expected, actual)
    }

    @Test
    fun setNearbyLocations_whenGetNearbyLocationUseCaseReturnList_thenNearbyLocationsIsSuccess() = runTest {
        val fakeNearbyLocations = FakeDataSource.fakeNearbyLocations

        coEvery { getNearbyLocationsUseCase(any()) } returns fakeNearbyLocations

        mapViewModel.setNearbyLocations(0.0, 0.0)

        val expected = ResponseUiState.Success(FakeDataSource.fakeNearbyLocations)

        val actual = mapViewModel.nearbyLocations.value
        assertEquals(expected, actual)
    }
}
