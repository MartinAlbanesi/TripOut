package com.example.turistaapp.trip_details.domain

import com.example.turistaapp.create_trip.data.database.repository.TripDBRepository
import javax.inject.Inject

class UpdateImagesFromDBUseCase @Inject constructor(
    private val tripDBRepository: TripDBRepository
) {
    suspend operator fun invoke(id: Int, images: List<String>) {
        tripDBRepository.updateImages(id, images)
    }
}