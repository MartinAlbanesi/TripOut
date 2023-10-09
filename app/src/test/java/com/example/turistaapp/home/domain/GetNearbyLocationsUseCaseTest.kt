package com.example.turistaapp.home.domain

import com.example.turistaapp.home.fake.FakeDataStore
import com.example.turistaapp.home.data.NearbySearchLocationRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test

class GetNearbyLocationsUseCaseTest{

    private lateinit var getNearbyLocationsUseCase: GetNearbyLocationsUseCase

    @RelaxedMockK
    private lateinit var nearbySearchLocationRepository: NearbySearchLocationRepository

    @Before
    fun setUp(){
        MockKAnnotations.init(this)
        getNearbyLocationsUseCase = GetNearbyLocationsUseCase(nearbySearchLocationRepository)
    }

    @Test
    fun `cuando el repositorio no tiene ningun error devolver la lista de NearbyLocation`() = runTest {

        val fakeNearbyLocations = FakeDataStore.fakeNearbyLocations

        //Given
        coEvery { nearbySearchLocationRepository.getNearbyLocation("location") } returns fakeNearbyLocations

        //When
        val result = getNearbyLocationsUseCase("location")

        //Then
        assertEquals(result!![0].name, fakeNearbyLocations[0].name)
    }

    @Test
    fun `cuando el repositorio devuelve null entonces devolver null`() = runTest {
        coEvery { nearbySearchLocationRepository.getNearbyLocation("location") } returns null

        val result = getNearbyLocationsUseCase("location")

        //Then
        assertNull(result)
    }
}