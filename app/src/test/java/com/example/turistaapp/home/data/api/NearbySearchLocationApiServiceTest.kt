package com.example.turistaapp.home.data.api

import com.example.turistaapp.core.Helper
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

class NearbySearchLocationApiServiceTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var nearbySearchLocationApiService: NearbySearchLocationApiService
    private lateinit var mockResponse: MockResponse

    @Before
    fun setUp() {
        mockResponse = MockResponse()
        mockWebServer = MockWebServer()
        nearbySearchLocationApiService = Retrofit.Builder()
            .baseUrl(mockWebServer.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NearbySearchLocationApiService::class.java)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun searchNearbyPlaces_code200_returnNearbySearchLocationApi() = runTest {
        val content = Helper.readFileResources("/fakeNearbyLocations.json")
        mockResponse.setResponseCode(200)
        mockResponse.setBody(content)
        mockWebServer.enqueue(mockResponse)

        val response = nearbySearchLocationApiService.searchNearbyPlaces("")
        mockWebServer.takeRequest()

        assertEquals(200, response.code())
        assertEquals(2, response.body()!!.nearbyLocationApis.size)
        assertEquals("Universidad Nacional de La Matanza", response.body()!!.nearbyLocationApis[0].name)
    }

    @Test
    fun searchNearbyPlaces_code400_returnNull() = runTest {
        mockResponse.setResponseCode(400)
        mockWebServer.enqueue(mockResponse)

        val response = nearbySearchLocationApiService.searchNearbyPlaces("")
        mockWebServer.takeRequest()
        assertEquals(400, response.code())
        assertNull(response.body())
    }
}
