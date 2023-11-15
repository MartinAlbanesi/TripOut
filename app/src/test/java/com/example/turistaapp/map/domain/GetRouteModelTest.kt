package com.example.turistaapp.map.domain

import com.example.turistaapp.map.data.IDirectionsRepository
import com.example.turistaapp.map.domain.models.RouteModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test


class GetRouteModelTest{

    private lateinit var getRouteModel: GetRouteModel

    @RelaxedMockK
    private lateinit var directionsRepository: IDirectionsRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        getRouteModel = GetRouteModel(directionsRepository)
    }

    @Test
    fun `invoke - When repository return RouteModel - then invoke return RouteModel` () = runTest{

        val expected = RouteModel("titi","","","",null)

        coEvery { directionsRepository.getDirections("", "", "") } returns expected

        val result = getRouteModel("", "", "", null)

        assertEquals(expected, result)
        assertEquals(expected.distance, result!!.distance)
    }

    @Test
    fun `invoke - When repository return null - then invoke return null` () = runTest{
        coEvery { directionsRepository.getDirections("", "", "") } returns null

        val result = getRouteModel("", "", "", null)

        assertEquals(null, result)
    }
}