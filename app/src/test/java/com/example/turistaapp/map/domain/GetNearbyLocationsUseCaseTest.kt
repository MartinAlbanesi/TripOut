package com.example.turistaapp.map.domain

import com.example.turistaapp.home.domain.GetNearbyLocationsUseCase
import com.example.turistaapp.home.data.NearbySearchLocationRepository
import com.example.turistaapp.map.fake.FakeDataSource
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import java.util.regex.Pattern

class GetNearbyLocationsUseCaseTest {

    private lateinit var getNearbyLocationsUseCase: GetNearbyLocationsUseCase

    @RelaxedMockK
    private lateinit var nearbySearchLocationRepository: NearbySearchLocationRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        getNearbyLocationsUseCase = GetNearbyLocationsUseCase(nearbySearchLocationRepository)
    }

    @Test
    fun `cuando el repositorio no tiene ningun error devolver la lista de NearbyLocation`() = runTest {
        val fakeNearbyLocations = FakeDataSource.fakeNearbyLocations

        // Given
        coEvery { nearbySearchLocationRepository.getNearbyLocation(any()) } returns fakeNearbyLocations

        // When
        val result = getNearbyLocationsUseCase("")

        // Then
        assertEquals(result!![0].name, fakeNearbyLocations[0].name)
    }

    @Test
    fun `cuando el repositorio devuelve null entonces devolver null`() = runTest {
        coEvery { nearbySearchLocationRepository.getNearbyLocation(any()) } returns null

        val result = getNearbyLocationsUseCase("")

        // Then
        assertNull(result)
    }
}
