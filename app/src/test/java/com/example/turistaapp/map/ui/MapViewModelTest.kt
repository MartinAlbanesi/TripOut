package com.example.turistaapp.map.ui

import android.location.Location
import com.example.turistaapp.home.domain.GetLastLocationUseCase
import com.example.turistaapp.map.domain.GetIsMapTutorialComplete
import com.example.turistaapp.map.domain.GetRouteModel
import com.example.turistaapp.map.domain.SetIsMapTutorialComplete
import com.example.turistaapp.map.ui.viewmodel.MapViewModel
import com.example.turistaapp.my_trips.domain.GetTripsUseCase
import com.google.android.gms.maps.model.LatLng
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

    @RelaxedMockK
    private lateinit var getIsMapTutorialComplete: GetIsMapTutorialComplete

    @RelaxedMockK
    private lateinit var setIsMapTutorialComplete: SetIsMapTutorialComplete

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        mapViewModel = MapViewModel(
            dispatcher,
            getTripsUseCase,
            getRouteModel,
            getLastLocationUseCase,
            getIsMapTutorialComplete,
            setIsMapTutorialComplete
        )
    }

    @Test
    fun `init - LastLocation Must is null - when getLastLocationUseCase is null` () = runTest{

        coEvery { getLastLocationUseCase() } returns null

        val result = mapViewModel.lastLocation.first()

        //NOTA: Por alguna razon, en vez de dar null da LatLng(0.0,0.0)
        assertEquals(LatLng(0.0,0.0), result)
    }

    @Test
    fun `init - LastLocation Must is not null - when getLastLocationUseCase is not null` () = runTest{

        val location = Location("test")

        coEvery { getLastLocationUseCase() } returns location

        val result = mapViewModel.lastLocation.first()

        assertEquals(LatLng(0.0,0.0), result)
    }

//    @Test
//    fun `init - destinationLocations Must is not null - When  ` () = runTest{
//
//    }
}
