package com.example.turistaapp.trip_details.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.turistaapp.trip_details.domain.UpdateImagesFromDBUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TripDetailsViewModel @Inject constructor(
    private val dispatcher: CoroutineDispatcher,
    private val updateImagesFromDBUseCase: UpdateImagesFromDBUseCase,
) : ViewModel() {

    fun updateImages(id: Int, images: List<String>) {
        viewModelScope.launch(dispatcher) {
            updateImagesFromDBUseCase(id, images)
        }
    }
}
