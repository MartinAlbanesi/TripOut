package com.example.turistaapp.create_trip.data.database.repository

import com.example.turistaapp.create_trip.data.database.dao.TripDao
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class TripDBRepositoryImplTest {

    @RelaxedMockK
    private lateinit var tripDao: TripDao

    private lateinit var tripDBRepository: TripDBRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        tripDBRepository = TripDBRepositoryImpl(tripDao)
    }

    @Test
    fun `getLocationsFromDestination - when TripDao getLocationsFromDestination return emptyList - returnEmptyList`() = runTest {
        // Given
        val expected = emptyList<String>()
        coEvery{ tripDao.getLocationsFromDestination() } returns expected

        // When
        val result = tripDBRepository.getLocationsFromDestination()

        // Then
        assertEquals(expected, result)
        assertEquals(0, result.size)
    }

    @Test
    fun `getLocationsFromDestination - when TripDao getLocationsFromDestination return String List - return String List`() =runTest {
        // Given
        val expected = listOf("titi", "wea")
        coEvery { tripDao.getLocationsFromDestination() } returns expected

        // When
        val result = tripDBRepository.getLocationsFromDestination()

        // Then
        assertEquals(expected, result)
        assertEquals(2, result.size)

        coVerify(exactly = 1) { tripDao.getLocationsFromDestination() }
    }
}