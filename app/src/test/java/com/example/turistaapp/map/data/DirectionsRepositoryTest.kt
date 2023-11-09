package com.example.turistaapp.map.data

import com.example.turistaapp.home.data.api.models.NearbySearchLocationApi
import com.example.turistaapp.map.data.api.model.DirectionsApi
import com.example.turistaapp.map.data.api.model.directions.DistanceDurationApi
import com.example.turistaapp.map.data.api.model.directions.LegApi
import com.example.turistaapp.map.data.api.model.directions.OverviewPolylineApi
import com.example.turistaapp.map.data.api.model.directions.RouteApi
import com.example.turistaapp.map.data.api.service.DirectionsApiService
import com.example.turistaapp.map.domain.models.RouteModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody
import org.junit.Assert
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class DirectionsRepositoryTest{

    private lateinit var directionsRepository: DirectionsRepository

    @RelaxedMockK
    private lateinit var directionsApiService: DirectionsApiService

    @Before
    fun setUp(){
        MockKAnnotations.init(this)
        directionsRepository = DirectionsRepository(directionsApiService)
    }

    @Test
    fun `getDirections - when directionsApiService return response from api - then return RouteModel ` () = runTest{

        val expected = RouteApi(
            legs = listOf(LegApi(DistanceDurationApi("1", 1),DistanceDurationApi("1", 1))),
            summary = "1",
            overviewPolyline = OverviewPolylineApi("titi")
        )
        coEvery { directionsApiService.getDirection(any(),any(),any()) } returns Response.success(
            DirectionsApi(
                routes = listOf(expected),
                status = "OK"
            )
        )
        val actual = directionsRepository.getDirections("","","")

        Assert.assertEquals(expected.summary,actual!!.summary)
    }

    @Test
    fun `getDirections - when directionsApiService return null - then return null` () = runTest{

        val response = Response.error<DirectionsApi>(
            404,
            ResponseBody.create(
                "application/json".toMediaTypeOrNull(),
                "{\"key\":[\"somestuff\"]}",
            ),
        )

        coEvery { directionsApiService.getDirection(any(),any(),any()) } returns response

        val result = directionsRepository.getDirections("","","")

        assertNull(result)

    }

}