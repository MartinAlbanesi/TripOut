package com.example.turistaapp.create_trip.data

import com.example.turistaapp.create_trip.data.network.place_details.PlaceDetailsApiService
import com.example.turistaapp.create_trip.data.network.place_details.models.GeometryApi
import com.example.turistaapp.create_trip.data.network.place_details.models.LocationApi
import com.example.turistaapp.create_trip.data.network.place_details.models.PlaceApi
import com.example.turistaapp.create_trip.data.network.place_details.models.PlaceResponseApi
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class PlaceDetailsRepositoryTest {

    @RelaxedMockK
    private lateinit var placeDetailsApiService: PlaceDetailsApiService

    private lateinit var placeDetailsRepository: IPlaceDetailsRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        placeDetailsRepository = PlaceDetailsRepository(placeDetailsApiService)
    }

    @Test
    fun `cuando el service retorna PlacesDetailsResponseApiModel entonces repositorio retorna LocationModel`() =
        runTest {
            // Given
            val expected = PlaceApi(
                geometryApi = GeometryApi(LocationApi(0.0, 0.0)),
                name = "name",
                placeID = "placeID",
                rating = 0.0,
                userRatings = 0,
                types = listOf("types"),
                address = "address",
                photoApi = emptyList()
            )
            val expected2 = Response.success(PlaceResponseApi(expected))

            coEvery {
                placeDetailsApiService.getPlaceDetails(any())
            }.returns(expected2)

            // When
            val result = placeDetailsRepository.getPlaceDetails("placeID")

            // Then
            assertEquals(expected.placeID, result!!.placeID)
        }
}
