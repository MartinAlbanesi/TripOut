package com.example.turistaapp.create_trip.domain

import com.example.turistaapp.create_trip.data.IPlaceDetailsRepository
import com.example.turistaapp.create_trip.domain.models.LocationModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class GetPlaceDetailsUseCaseTest {

    private lateinit var getPlaceDetailsUseCase: GetPlaceDetailsUseCase

    @RelaxedMockK
    private lateinit var placeDetailsRepository: IPlaceDetailsRepository
    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        getPlaceDetailsUseCase = GetPlaceDetailsUseCase(placeDetailsRepository)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun cuandoElRepositorioRetornaLocationModelEntoncesDevolverUnLocationModel() = runTest {
        // Given
        val expected = LocationModel("placeId", "name", "", 0.0, 0, "phone", 0.0, 0.0, listOf(""))

        coEvery {
            placeDetailsRepository.getPlaceDetails(any())
        }.returns(expected)

        //When
        val result = getPlaceDetailsUseCase("placeId")

        //Then
        assertEquals(expected, result)
        assertEquals(expected.placeID, result!!.placeID)

    }
}