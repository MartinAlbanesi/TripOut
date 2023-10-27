package com.example.turistaapp.map.data.api

import com.example.turistaapp.core.Helper
import com.example.turistaapp.map.data.api.service.DirectionsApiService
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DirectionsApiServiceTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var mockResponse: MockResponse
    private lateinit var directionsApiService: DirectionsApiService

    @Before
    fun setUp() {
        mockResponse = MockResponse()
        mockWebServer = MockWebServer()
        directionsApiService = Retrofit.Builder()
            .baseUrl(mockWebServer.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DirectionsApiService::class.java)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }
    
    @Test
    fun `getDirection - Get 200 code - return DirectionsApi` () = runTest{
        val content = Helper.readFileResources("/fakeDirections.json")
        mockResponse.setResponseCode(200)
        mockResponse.setBody(content)
        mockWebServer.enqueue(mockResponse)

        val response = directionsApiService.getDirection("", "")
        mockWebServer.takeRequest()

        assertEquals(200, response.code())
        assertEquals(response.body()!!.status, "OK")
        assertEquals(response.body()!!.routes[0].summary, "ON-401 E")

    }

    @Test
    fun searchNearbyPlaces_code400_returnNull() = runTest {
        mockResponse.setResponseCode(400)
        mockWebServer.enqueue(mockResponse)

        val response = directionsApiService.getDirection("", "")
        mockWebServer.takeRequest()
        assertEquals(400, response.code())
        assertNull(response.body())
    }
}