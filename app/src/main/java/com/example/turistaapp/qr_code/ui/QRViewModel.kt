package com.example.turistaapp.qr_code.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class QRViewModel @Inject constructor(
    private val dispatcher: CoroutineDispatcher,
) : ViewModel() {

    private var _isQRDialogOpen = MutableStateFlow(false)
    val isQRDialogOpen = _isQRDialogOpen

    fun onIsQRDialogOpenChange(isQRDialogOpen: Boolean) {
        _isQRDialogOpen.value = isQRDialogOpen
    }

    private var _dataQRSelected = MutableStateFlow("")
    val dataQRSelected = _dataQRSelected

    fun onDataQRSelectedChange(dataQRSelected: String) {
        _dataQRSelected.value = dataQRSelected
    }
}
