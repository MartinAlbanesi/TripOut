package com.example.turistaapp.create_trip.data

import com.example.turistaapp.create_trip.data.network.place_details.PlaceDetailsApiService
import com.example.turistaapp.create_trip.data.network.place_details.models.PlaceApiModel
import com.example.turistaapp.create_trip.data.network.place_details.models.toLocationModel
import com.example.turistaapp.create_trip.domain.models.LocationModel
import javax.inject.Inject

interface IPlaceDetailsRepository {
    suspend fun getPlaceDetails(placeID: String): LocationModel?

}
class PlaceDetailsRepository @Inject constructor(
    private val placeDetailsApiService: PlaceDetailsApiService
): IPlaceDetailsRepository{
    override suspend fun getPlaceDetails(placeID: String): LocationModel? {
        return placeDetailsApiService.getPlaceDetails(placeID).body()?.result?.toLocationModel()
    }
}
