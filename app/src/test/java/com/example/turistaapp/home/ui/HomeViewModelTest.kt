package com.example.turistaapp.home.ui

import com.example.turistaapp.core.utils.ResponseUiState
import com.example.turistaapp.home.domain.GetNearbyLocationsUseCase
import com.example.turistaapp.home.fake.FakeDataSource
import com.example.turistaapp.home.ui.viewmodel.HomeViewModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class HomeViewModelTest {

    private lateinit var homeViewModel: HomeViewModel

    private val dispatcher = UnconfinedTestDispatcher()

    @RelaxedMockK
    private lateinit var getNearbyLocationsUseCase: GetNearbyLocationsUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        homeViewModel = HomeViewModel(dispatcher,getNearbyLocationsUseCase)
    }

    @Test
    fun setNearbyLocations_whenGetNearbyLocationUseCaseReturnNull_thenNearbyLocationsIsError() = runTest {

        coEvery { getNearbyLocationsUseCase(any()) } returns null

        homeViewModel.setNearbyLocations(0.0,0.0)

        var actual = homeViewModel.nearbyLocations.value

        val expected = ResponseUiState.Error("No se encontraron lugares cercanos")

        assertEquals(expected.message, (actual as ResponseUiState.Error).message)
        assertEquals(expected,actual)
    }

    @Test
    fun setNearbyLocations_whenGetNearbyLocationUseCaseReturnList_thenNearbyLocationsIsSuccess() = runTest {

        val fakeNearbyLocations = FakeDataSource.fakeNearbyLocations

        coEvery { getNearbyLocationsUseCase(any()) } returns fakeNearbyLocations

        homeViewModel.setNearbyLocations(0.0,0.0)

        val expected = ResponseUiState.Success(FakeDataSource.fakeNearbyLocations)

        val actual = homeViewModel.nearbyLocations.value
        assertEquals(expected,actual)
    }

//    @Test
//    fun setNearbyLocations_whenGetNearbyLocationUseCaseReturnException_thenNearbyLocationsIsErrorWithMessageException() = runTest {
//
//        coEvery { getNearbyLocationsUseCase(any()) }.throws( IndexOutOfBoundsException("Error"))
//
//        homeViewModel.setNearbyLocations(0.0,0.0)
//
//        val expected = ResponseUiState.Error("Error")
//
//        val actual = homeViewModel.nearbyLocations.value
////        assertEquals(expected,actual)
//        assertThrows(IndexOutOfBoundsException::class.java) {
//            throw IndexOutOfBoundsException("Error")
//        }
//    }
}