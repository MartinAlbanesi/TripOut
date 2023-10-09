package com.example.turistaapp.home.data

import com.example.turistaapp.home.data.api.NearbySearchLocationApiService
import com.example.turistaapp.home.data.api.model.NearbySearchLocationApi
import com.example.turistaapp.home.fake.FakeDataStore
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class NearbySearchLocationRepositoryTest{

    private lateinit var nearbySearchLocationRepository: NearbySearchLocationRepository

    @RelaxedMockK
    private lateinit var nearbySearchLocationApiService: NearbySearchLocationApiService

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        nearbySearchLocationRepository = NearbySearchLocationRepository(nearbySearchLocationApiService)
    }

    @Test
    fun getNearbyLocation_serviceReturnResponseFromApi_returnNearbyLocationList() = runTest {
        //Given
        val fakeNearbyLocations = FakeDataStore.fakeNearbyLocationApi

        val response = Response.success(fakeNearbyLocations)

        coEvery { nearbySearchLocationApiService.searchNearbyPlaces("") } returns response

        //When
        val result = nearbySearchLocationRepository.getNearbyLocation("")

        //Then
        assertEquals("name1", result!![0].name)
    }

    @Test
    fun getNearbyLocation_serviceReturnNull_returnNull() = runTest {

        //Given
        val response = Response.error<NearbySearchLocationApi>(
            404,
            ResponseBody.create(
                "application/json".toMediaTypeOrNull(),
                "{\"key\":[\"somestuff\"]}"
            )
        );

        coEvery { nearbySearchLocationApiService.searchNearbyPlaces("") } returns response

        //When
        val result = nearbySearchLocationRepository.getNearbyLocation("")

        //Then
        assertNull(result)
    }
}