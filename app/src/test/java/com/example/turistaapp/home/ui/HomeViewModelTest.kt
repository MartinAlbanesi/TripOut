package com.example.turistaapp.home.ui

import android.location.Location
import com.example.turistaapp.core.utils.ResponseUiState
import com.example.turistaapp.home.domain.GetLastLocationUseCase
import com.example.turistaapp.home.domain.GetNearbyLocationsUseCase
import com.example.turistaapp.home.domain.GetRandomLocationFromDB
import com.example.turistaapp.map.fake.FakeDataSource
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class HomeViewModelTest {

    private lateinit var homeViewModel: HomeViewModel

    @OptIn(ExperimentalCoroutinesApi::class)
    private val dispatcher = UnconfinedTestDispatcher()

    @RelaxedMockK
    private lateinit var getNearbyLocationsUseCase: GetNearbyLocationsUseCase

    @RelaxedMockK
    private lateinit var getRandomLocationFromDB: GetRandomLocationFromDB

    @RelaxedMockK
    private lateinit var getLastLocationUseCase: GetLastLocationUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        homeViewModel = HomeViewModel(
            dispatcher,
            getNearbyLocationsUseCase,
            getRandomLocationFromDB,
            getLastLocationUseCase
        )
    }

    @Test
    fun getRandomLocation_whenGetNearbyLocationUseCaseReturnNull_thenNearbyLocationsIsError() =
        runTest {
            coEvery { getNearbyLocationsUseCase(any()) } returns null

            val actual = homeViewModel.nearbyLocations.value

            val expected = ResponseUiState.Error("Por ahora no hay lugares para recomendar")

            assertEquals(expected.message, (actual as ResponseUiState.Error).message)
            assertEquals(expected, actual)
        }

    @Test
    fun getRandomLocation_whenGetNearbyLocationUseCaseReturnList_thenNearbyLocationsIsSuccess() =
        runTest {
            val fakeNearbyLocations = FakeDataSource.fakeNearbyLocations[0]

//            coEvery { getRandomLocationFromDB() } returns fakeNearbyLocations
            coEvery { getNearbyLocationsUseCase(any()) } returns FakeDataSource.fakeNearbyLocations
//            coEvery { getLastLocationUseCase() } returns Location("")

            homeViewModel.getRandomLocation()

            val expected = ResponseUiState.Success(FakeDataSource.fakeNearbyLocations)

            val actual = homeViewModel.nearbyLocations.value
            assertEquals(expected, actual)
        }

}