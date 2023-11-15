package com.example.turistaapp.setting.ui

import android.content.Context
import android.graphics.Bitmap
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.CameraController
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CameraViewModel : ViewModel() {

    private val _cameraController: MutableStateFlow<LifecycleCameraController?> =
        MutableStateFlow(null)
    val cameraController: StateFlow<LifecycleCameraController?> = _cameraController.asStateFlow()


    private val _bitmaps = MutableStateFlow<List<Bitmap>>(emptyList())
    val bitmap = _bitmaps.asStateFlow()


    fun initializeCamera(context: Context) {
        viewModelScope.launch {
            viewModelScope.launch {
                val cameraController = LifecycleCameraController(context)
                _cameraController.value = cameraController
            }

            fun takePhoto(bitmap: Bitmap) {
                _bitmaps.value += bitmap
            }
        }

    }
}