package com.example.turistaapp.create_trip.data.network.place_details

import com.example.turistaapp.core.Helper
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PlaceDetailsApiServiceTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var placeDetailsApiService : PlaceDetailsApiService
    private lateinit var mockResponse: MockResponse

    @Before
    fun setUp() {
        mockResponse = MockResponse()
        mockWebServer = MockWebServer()
        placeDetailsApiService = Retrofit.Builder()
            .baseUrl(mockWebServer.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PlaceDetailsApiService::class.java)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun getPlaceDetails_code200_returnPlacesDetailsResponseApiModel() = runTest {
        val content = Helper.readFileResources("/fake.json")
        mockResponse.setResponseCode(200)
        mockResponse.setBody(content)
        mockWebServer.enqueue(mockResponse)

        val response = placeDetailsApiService.getPlaceDetails("ChIJN1t_tDeuEmsRUsoyG83frY4")
        mockWebServer.takeRequest()

        assertEquals(200, response.code())
        assertEquals("ChIJN1t_tDeuEmsRUsoyG83frY4", response.body()!!.result.placeID)
    }
}