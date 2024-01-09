package com.example.turistaapp.trip_details.domain

import android.net.Uri
import com.example.turistaapp.core.data.database.TripDBRepository
import javax.inject.Inject

class UpdateImagesFromDBUseCase @Inject constructor(
    private val tripDBRepository: TripDBRepository,
) {
    suspend operator fun invoke(id: Int, images: Uri) {
        tripDBRepository.updateImages(id, images)
    }
}
