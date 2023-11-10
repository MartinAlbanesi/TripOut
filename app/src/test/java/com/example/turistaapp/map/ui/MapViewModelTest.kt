package com.example.turistaapp.map.ui

import com.example.turistaapp.core.utils.ResponseUiState
import com.example.turistaapp.home.domain.GetLastLocationUseCase
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

    @RelaxedMockK
    private lateinit var getLastLocationUseCase: GetLastLocationUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        mapViewModel = MapViewModel(
            dispatcher,
            getTripsUseCase,
            getRouteModel,
            getLastLocationUseCase
        )
    }
}
