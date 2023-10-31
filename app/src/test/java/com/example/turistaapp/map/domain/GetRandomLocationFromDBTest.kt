package com.example.turistaapp.map.domain

import com.example.turistaapp.create_trip.FakeDataBaseSource
import com.example.turistaapp.create_trip.domain.GetDestinationLocationsFromDataBase
import com.example.turistaapp.home.domain.GetRandomLocationFromDB
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class GetRandomLocationFromDBTest {

    @RelaxedMockK
    private lateinit var getDestinationLocationsFromDataBase: GetDestinationLocationsFromDataBase

    private lateinit var getRandomLocationFromDB: GetRandomLocationFromDB

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        getRandomLocationFromDB = GetRandomLocationFromDB(getDestinationLocationsFromDataBase)
    }

    @Test
    fun invoke_GetDestinationLocationFromDBIsEmpty_returnNull() = runTest {
        coEvery { getDestinationLocationsFromDataBase() } returns emptyList()

        val result = getRandomLocationFromDB()

        assertNull(result)
    }

    @Test
    fun `invoke - GetDestinationLocationFromDB return LocationList - return random LocationModel `() = runTest {
        val expected = listOf(
            FakeDataBaseSource.locationModel,
            FakeDataBaseSource.locationModel,
        )

        coEvery { getDestinationLocationsFromDataBase() } returns expected

        val result = getRandomLocationFromDB()

        assertTrue(expected.contains(result))
        assertNotNull(result)
    }
}
