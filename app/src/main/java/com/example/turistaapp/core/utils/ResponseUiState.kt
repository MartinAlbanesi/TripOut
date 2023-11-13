package com.example.turistaapp.core.utils

sealed interface ResponseUiState {
    data class Success<T>(val values: T) : ResponseUiState
    object Loading : ResponseUiState
    data class Error(val message: String) : ResponseUiState
}
