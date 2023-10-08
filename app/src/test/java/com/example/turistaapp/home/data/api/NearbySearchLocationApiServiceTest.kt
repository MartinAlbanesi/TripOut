package com.example.turistaapp.home.data.api

import com.example.turistaapp.core.Helper
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NearbySearchLocationApiServiceTest {

    lateinit var mockWebServer: MockWebServer
    lateinit var nearbySearchLocationApiService : NearbySearchLocationApiService

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        nearbySearchLocationApiService = Retrofit.Builder()
            .baseUrl(mockWebServer.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NearbySearchLocationApiService::class.java)
    }



    @Test
    fun searchNearbyPlaces_code200_returnNearbySearchLocationApi() = runTest {
        val mockResponse = MockResponse()
        val content = Helper.readFileResources("/fake.json")
        mockResponse.setResponseCode(200)
        mockResponse.setBody(content)
        mockWebServer.enqueue(mockResponse)

        val response = nearbySearchLocationApiService.searchNearbyPlaces("")
        mockWebServer.takeRequest()

        Assert.assertEquals(200, response.code())
        Assert.assertEquals(2, response.body()!!.nearbyLocationApis.size)
        Assert.assertEquals("Universidad Nacional de La Matanza", response.body()!!.nearbyLocationApis[0].name)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }
}