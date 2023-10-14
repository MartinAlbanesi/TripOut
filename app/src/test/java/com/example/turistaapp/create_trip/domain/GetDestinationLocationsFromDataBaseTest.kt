package com.example.turistaapp.create_trip.domain

import com.example.turistaapp.core.utils.GsonConverter
import com.example.turistaapp.create_trip.data.database.repository.TripDBRepository
import com.example.turistaapp.create_trip.domain.models.LocationModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class GetDestinationLocationsFromDataBaseTest{

    @RelaxedMockK
    private lateinit var tripDBRepository: TripDBRepository

    private lateinit var getDestinationLocationsFromDataBase: GetDestinationLocationsFromDataBase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        getDestinationLocationsFromDataBase = GetDestinationLocationsFromDataBase(tripDBRepository)
    }

    @Test
    fun invoke_locationsListIsEmpty_returnEmptyList() = runTest {

        coEvery { tripDBRepository.getLocationsFromDestination() } returns emptyList()

        val result = getDestinationLocationsFromDataBase()

        assertTrue(result.isEmpty())
        assertEquals(0, result.size)
    }

    @Test
    fun invoke_locationsReturnLocationList_returnLocationModelList() = runTest {

        val expected = listOf(
            LocationModel("titi",
                "Location 1",
                "Description 1",
                0.0,
                1,
                null,
                -34.6246832,
                -58.487447,
                null,
            ),
            LocationModel("toto",
                "Location 2",
                "Description 2",
                0.0,
                1,
                null,
                -34.6246832,
                -58.487447,
                null,
            ),
        )

        val locationList = expected.map { GsonConverter.toJson(it) }

        coEvery { tripDBRepository.getLocationsFromDestination() } returns locationList

        val result = getDestinationLocationsFromDataBase()

        assertEquals(2, result.size)
        assertEquals(expected, result)
        assertEquals(expected[0].name, result[0].name)
    }
}