package com.example.turistaapp.home.domain

import com.example.turistaapp.create_trip.domain.GetDestinationLocationsFromDataBase
import com.example.turistaapp.create_trip.domain.models.LocationModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class GetRandomLocationFromDBTest{

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
    fun `invoke - GetDestinationLocationFromDB return LocationList - return random LocationModel ` () = runTest{

        val expected = listOf(
            LocationModel("titi",
                "Location 1",
                "Description 1",
                0.0,
                1,
                null,
                1.1,
                2.2,
                null,
            ),
            LocationModel("toto",
                "Location 2",
                "Description 2",
                0.0,
                1,
                null,
                1.1,
                2.2,
                null,
            ),
        )

        coEvery { getDestinationLocationsFromDataBase() } returns expected

        val result = getRandomLocationFromDB()

        assertTrue(expected.contains(result))
        assertNotNull(result)
    }
}