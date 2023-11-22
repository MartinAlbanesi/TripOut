package com.example.turistaapp.trip_details.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.turistaapp.trip_details.domain.UpdateImagesFromDBUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

class TripDetailsViewModel @Inject constructor(
    private val dispatcher: CoroutineDispatcher,
    private val updateImagesFromDBUseCase: UpdateImagesFromDBUseCase,
) : ViewModel(){

    fun updateImages(id: Int, images: List<String>) {
        viewModelScope.launch(dispatcher) {
            updateImagesFromDBUseCase(id, images)
        }
    }
}