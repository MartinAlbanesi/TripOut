package com.example.turistaapp.setting.domain

import com.example.turistaapp.core.data.database.TripDBRepository
import javax.inject.Inject

class UpdateImagesFromDBUseCase @Inject constructor(
    private val tripDBRepository: TripDBRepository,
) {
    suspend operator fun invoke(id: Int, images: List<String>) {
        tripDBRepository.updateImages(id, images)
    }
}
