package com.example.turistaapp.map.ui

import android.location.Location
import com.example.turistaapp.home.domain.GetLastLocationUseCase
import com.example.turistaapp.map.domain.GetRouteModel
import com.example.turistaapp.map.ui.viewmodel.MapViewModel
import com.example.turistaapp.my_trips.domain.GetTripsUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
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

    @Test
    fun `init & getLastLocation - When getLastLocationUseCase is not null - then lastLocation flow get LastLocation from UseCase` () = runTest{
        val expected = Location("test").apply {
            latitude = 1.0
            longitude = 1.0
        }

//        coEvery { getLastLocationUseCase() } returns expected

        val actual = mapViewModel.lastLocation.first()

        assertEquals(expected.latitude, actual?.latitude)
    }
}
